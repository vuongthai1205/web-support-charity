/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.repository.impl;

import com.mycompany.pojo.BaiViet;
import com.mycompany.pojo.DuAnTuThien;
import com.mycompany.pojo.ThamGiaDuAn;
import com.mycompany.pojo.TvBinhLuanBv;
import com.mycompany.pojo.TvThichBv;
import com.mycompany.repository.ThongKeRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vuongthai1205
 */
@Repository
@Transactional
public class ThongKeRepositoryImpl implements ThongKeRepository {

    public static final SimpleDateFormat F = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public List<Object[]> stats(Map<String, String> params) {
        Session session = this.sessionFactory.getObject().getCurrentSession();

        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root rPost = q.from(BaiViet.class);

        // Tạo subquery để đếm số like cho mỗi bài viết
        Subquery<Long> likeCountSubquery = q.subquery(Long.class);
        Root likeCountRoot = likeCountSubquery.from(TvThichBv.class);
        likeCountSubquery.select(b.countDistinct(likeCountRoot.get("tvThichBvPK")));
        likeCountSubquery.where(
                b.and(
                        b.equal(likeCountRoot.get("baiViet"), rPost.get("maBaiViet")),
                        b.equal(likeCountRoot.get("daThich"), 1)
                )
        );

        // Join comment bằng LEFT JOIN
        Join<TvBinhLuanBv, BaiViet> joinComment = rPost.join("tvBinhLuanBvSet", JoinType.LEFT);

        List<Predicate> predicates = new ArrayList<>();

        // Điều kiện: Ngày tạo post từ fromDate đến toDate
        if (params != null) {
            String fd = params.get("fromDate");
            if (fd != null && !fd.isEmpty()) {
                try {
                    predicates.add(b.greaterThanOrEqualTo(rPost.get("ngayTao"), F.parse(fd)));
                } catch (ParseException ex) {
                    Logger.getLogger(ThongKeRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            String td = params.get("toDate");
            if (td != null && !td.isEmpty()) {
                try {
                    predicates.add(b.lessThanOrEqualTo(rPost.get("ngayCapNhat"), F.parse(td)));
                } catch (ParseException ex) {
                    Logger.getLogger(ThongKeRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        // Select và Group by theo ID và Title, và kết hợp với subquery để tính số like
        q.multiselect(rPost.get("maBaiViet"), rPost.get("tieuDe"),
                likeCountSubquery.getSelection(),
                b.countDistinct(joinComment.get("maBinhLuan")));
        q.groupBy(rPost.get("maBaiViet"), rPost.get("tieuDe"));

        // Áp dụng điều kiện
        q.where(predicates.toArray(new Predicate[0]));

        // Sắp xếp theo ID giảm dần
        q.orderBy(b.desc(rPost.get("maBaiViet")));

        Query query = session.createQuery(q);

        return query.getResultList();
    }

    @Override
    public List<Object[]> statsProject(Map<String, String> params) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
        Root root = query.from(DuAnTuThien.class);

        Join<ThamGiaDuAn, DuAnTuThien> projectJoin = root.join("thamGiaDuAnSet", JoinType.LEFT);

        // Định nghĩa các trường bạn muốn thống kê
        query.multiselect(
                root.get("maDuAn").alias("maDuAn"),
                root.get("tenDuAn").alias("tenDuAn"),
                cb.countDistinct(projectJoin.get("thamGiaDuAnPK").get("maThanhVien")).alias("soLuongThanhVien")
        );

        // Xây dựng các điều kiện dựa trên tham số đầu vào (params)
        List<Predicate> predicates = new ArrayList<>();

        if (params != null) {
            String fd = params.get("fromDate");
            if (fd != null && !fd.isEmpty()) {
                try {
                    predicates.add(cb.greaterThanOrEqualTo(root.get("ngayTao"), F.parse(fd)));
                } catch (ParseException ex) {
                    Logger.getLogger(ThongKeRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            String td = params.get("toDate");
            if (td != null && !td.isEmpty()) {
                try {
                    predicates.add(cb.lessThanOrEqualTo(root.get("ngayCapNhat"), F.parse(td)));
                } catch (ParseException ex) {
                    Logger.getLogger(ThongKeRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        // Thêm điều kiện vào truy vấn
        if (!predicates.isEmpty()) {
            query.where(predicates.toArray(new Predicate[0]));
        }

        // Nhóm theo mã dự án
        query.groupBy(root.get("maDuAn"));

        // Thực hiện truy vấn
        Query<Object[]> typedQuery = session.createQuery(query);
        List<Object[]> results = typedQuery.getResultList();

        return results;
    }

}
