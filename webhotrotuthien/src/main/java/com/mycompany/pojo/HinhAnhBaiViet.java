/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pojo;

import com.googlecode.jmapper.annotations.JMap;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author vuongthai1205
 */
@Entity
@Table(name = "hinh_anh_bai_viet")
@NamedQueries({
    @NamedQuery(name = "HinhAnhBaiViet.findAll", query = "SELECT h FROM HinhAnhBaiViet h")})
public class HinhAnhBaiViet implements Serializable {
    
    @PrePersist
    protected void onCreate(){
        this.ngayTao=new Date(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate(){
        this.ngayCapNhat=new Date(System.currentTimeMillis());
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MaHinhBaiViet")
    private Integer maHinhBaiViet;
    @Size(max = 200)
    @Column(name = "DuongDanHinh")
    @JMap("link")
    private String duongDanHinh;
    @Column(name = "NgayTao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayTao;
    @Column(name = "NgayCapNhat")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayCapNhat;
    @ManyToOne
    @JoinColumn(name = "MaBaiViet", referencedColumnName = "MaBaiViet")
    private BaiViet baiViet;

    public HinhAnhBaiViet() {
    }

    public HinhAnhBaiViet(Integer maHinhBaiViet) {
        this.maHinhBaiViet = maHinhBaiViet;
    }

    public Integer getMaHinhBaiViet() {
        return maHinhBaiViet;
    }

    public void setMaHinhBaiViet(Integer maHinhBaiViet) {
        this.maHinhBaiViet = maHinhBaiViet;
    }

    public String getDuongDanHinh() {
        return duongDanHinh;
    }

    public void setDuongDanHinh(String duongDanHinh) {
        this.duongDanHinh = duongDanHinh;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgayCapNhat() {
        return ngayCapNhat;
    }

    public void setNgayCapNhat(Date ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }

    public BaiViet getBaiViet() {
        return baiViet;
    }

    public void setBaiViet(BaiViet baiViet) {
        this.baiViet = baiViet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maHinhBaiViet != null ? maHinhBaiViet.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HinhAnhBaiViet)) {
            return false;
        }
        HinhAnhBaiViet other = (HinhAnhBaiViet) object;
        if ((this.maHinhBaiViet == null && other.maHinhBaiViet != null) || (this.maHinhBaiViet != null && !this.maHinhBaiViet.equals(other.maHinhBaiViet))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.pojo.HinhAnhBaiViet[ maHinhBaiViet=" + maHinhBaiViet + " ]";
    }
    
}
