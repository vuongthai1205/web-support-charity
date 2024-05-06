/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.googlecode.jmapper.annotations.JMap;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author vuongthai1205
 */
@Entity
@Table(name = "bai_viet")
@NamedQueries({
    @NamedQuery(name = "BaiViet.findAll", query = "SELECT b FROM BaiViet b")})
public class BaiViet implements Serializable {

    @PrePersist
    protected void onCreate() {
        this.ngayTao = new Date(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate() {
        this.ngayCapNhat = new Date(System.currentTimeMillis());
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MaBaiViet")
    private Integer maBaiViet;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 105)
    @Column(name = "TieuDe")
    @JMap("title")
    private String tieuDe;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "NoiDung")
    @JMap("content")
    private String noiDung;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "GiaKhoiDiem")
    @JMap("startPrice")
    private Double giaKhoiDiem;
    @Column(name = "ThoiGianBatDau")
    @JMap("auctionStartTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date thoiGianBatDau;
    @Column(name = "ThoiGianKetThuc")
    @Temporal(TemporalType.TIMESTAMP)
    @JMap("auctionEndTime")
    private Date thoiGianKetThuc;
    @Column(name = "NgayTao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayTao;
    @Column(name = "NgayCapNhat")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayCapNhat;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "baiViet", fetch = FetchType.EAGER)
    private List<HinhAnhBaiViet> hinhAnhBaiViets;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "baiViet")
    private Set<DauGia> dauGiaSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "baiViet")
    private Set<TvBinhLuanBv> tvBinhLuanBvSet;
    @JoinColumn(name = "MaThanhVien", referencedColumnName = "MaThanhVien")
    @ManyToOne(optional = false)
    private ThanhVien maThanhVien;
    @JoinColumn(name = "TrangThaiDauGia", referencedColumnName = "MaTrangThaiDauGia")
    @ManyToOne
    private TrangThaiDauGia trangThaiDauGia;

    @Transient
    @JsonIgnore
    private MultipartFile[] file;

    public BaiViet() {
    }

    public BaiViet(Integer maBaiViet) {
        this.maBaiViet = maBaiViet;
    }

    public BaiViet(Integer maBaiViet, String tieuDe, String noiDung) {
        this.maBaiViet = maBaiViet;
        this.tieuDe = tieuDe;
        this.noiDung = noiDung;
    }

    public Integer getMaBaiViet() {
        return maBaiViet;
    }

    public void setMaBaiViet(Integer maBaiViet) {
        this.maBaiViet = maBaiViet;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public Double getGiaKhoiDiem() {
        return giaKhoiDiem;
    }

    public void setGiaKhoiDiem(Double giaKhoiDiem) {
        this.giaKhoiDiem = giaKhoiDiem;
    }

    public Date getThoiGianBatDau() {
        return thoiGianBatDau;
    }

    public void setThoiGianBatDau(Date thoiGianBatDau) {
        this.thoiGianBatDau = thoiGianBatDau;
    }

    public Date getThoiGianKetThuc() {
        return thoiGianKetThuc;
    }

    public void setThoiGianKetThuc(Date thoiGianKetThuc) {
        this.thoiGianKetThuc = thoiGianKetThuc;
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

    public List<HinhAnhBaiViet> getHinhAnhBaiViets() {
        return hinhAnhBaiViets;
    }

    public void setHinhAnhBaiViets(List<HinhAnhBaiViet> hinhAnhBaiViets) {
        this.hinhAnhBaiViets = hinhAnhBaiViets;
    }

    public Set<DauGia> getDauGiaSet() {
        return dauGiaSet;
    }

    public void setDauGiaSet(Set<DauGia> dauGiaSet) {
        this.dauGiaSet = dauGiaSet;
    }

    public Set<TvBinhLuanBv> getTvBinhLuanBvSet() {
        return tvBinhLuanBvSet;
    }

    public void setTvBinhLuanBvSet(Set<TvBinhLuanBv> tvBinhLuanBvSet) {
        this.tvBinhLuanBvSet = tvBinhLuanBvSet;
    }

    public ThanhVien getMaThanhVien() {
        return maThanhVien;
    }

    public void setMaThanhVien(ThanhVien maThanhVien) {
        this.maThanhVien = maThanhVien;
    }

    public TrangThaiDauGia getTrangThaiDauGia() {
        return trangThaiDauGia;
    }

    public void setTrangThaiDauGia(TrangThaiDauGia trangThaiDauGia) {
        this.trangThaiDauGia = trangThaiDauGia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maBaiViet != null ? maBaiViet.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BaiViet)) {
            return false;
        }
        BaiViet other = (BaiViet) object;
        if ((this.maBaiViet == null && other.maBaiViet != null) || (this.maBaiViet != null && !this.maBaiViet.equals(other.maBaiViet))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.pojo.BaiViet[ maBaiViet=" + maBaiViet + " ]";
    }

    /**
     * @return the file
     */
    public MultipartFile[] getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(MultipartFile[] file) {
        this.file = file;
    }

}
