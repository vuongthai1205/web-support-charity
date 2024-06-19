/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Collection;
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
@Table(name = "du_an_tu_thien")
@NamedQueries({
    @NamedQuery(name = "DuAnTuThien.findAll", query = "SELECT d FROM DuAnTuThien d")})
public class DuAnTuThien implements Serializable {

    @PrePersist
    protected void onCreate() {
        this.ngayTao = new Date(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate() {
        this.ngayCapNhat = new Date(System.currentTimeMillis());
    }
    @Column(name = "DaDuyet")
    private Short daDuyet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "duAn", fetch = FetchType.EAGER)
    private List<HinhAnhDuAn> hinhAnhDuAnList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MaDuAn")
    private Integer maDuAn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "TenDuAn")
    private String tenDuAn;
    @Size(max = 200)
    @Column(name = "MucDich")
    private String mucDich;
    @Column(name = "ThoiGianBatDau")
    @Temporal(TemporalType.TIMESTAMP)
    private Date thoiGianBatDau;
    @Column(name = "ThoiGianKetThuc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date thoiGianKetThuc;
    @Size(max = 200)
    @Column(name = "DiaDiem")
    private String diaDiem;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SoTienHuyDong")
    private Double soTienHuyDong;
    @Column(name = "NgayTao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayTao;
    @Column(name = "NgayCapNhat")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayCapNhat;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "duAnTuThien")
    private Set<ThamGiaDuAn> thamGiaDuAnSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "duAnTuThien")
    private Set<TvBinhLuanDa> tvBinhLuanDaSet;
    @JoinColumn(name = "MaThanhVienTaoDA", referencedColumnName = "MaThanhVien")
    @ManyToOne(optional = false)
    private ThanhVien maThanhVienTaoDA;
    @Transient
    @JsonIgnore
    private MultipartFile[] file;
    public DuAnTuThien() {
    }

    public DuAnTuThien(Integer maDuAn) {
        this.maDuAn = maDuAn;
    }

    public DuAnTuThien(Integer maDuAn, String tenDuAn) {
        this.maDuAn = maDuAn;
        this.tenDuAn = tenDuAn;
    }

    public Integer getMaDuAn() {
        return maDuAn;
    }

    public void setMaDuAn(Integer maDuAn) {
        this.maDuAn = maDuAn;
    }

    public String getTenDuAn() {
        return tenDuAn;
    }

    public void setTenDuAn(String tenDuAn) {
        this.tenDuAn = tenDuAn;
    }

    public String getMucDich() {
        return mucDich;
    }

    public void setMucDich(String mucDich) {
        this.mucDich = mucDich;
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

    

    public String getDiaDiem() {
        return diaDiem;
    }

    public void setDiaDiem(String diaDiem) {
        this.diaDiem = diaDiem;
    }

    public Double getSoTienHuyDong() {
        return soTienHuyDong;
    }

    public void setSoTienHuyDong(Double soTienHuyDong) {
        this.soTienHuyDong = soTienHuyDong;
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

    public Set<ThamGiaDuAn> getThamGiaDuAnSet() {
        return thamGiaDuAnSet;
    }

    public void setThamGiaDuAnSet(Set<ThamGiaDuAn> thamGiaDuAnSet) {
        this.thamGiaDuAnSet = thamGiaDuAnSet;
    }

    public ThanhVien getMaThanhVienTaoDA() {
        return maThanhVienTaoDA;
    }

    public void setMaThanhVienTaoDA(ThanhVien maThanhVienTaoDA) {
        this.maThanhVienTaoDA = maThanhVienTaoDA;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maDuAn != null ? maDuAn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DuAnTuThien)) {
            return false;
        }
        DuAnTuThien other = (DuAnTuThien) object;
        if ((this.maDuAn == null && other.maDuAn != null) || (this.maDuAn != null && !this.maDuAn.equals(other.maDuAn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.pojo.DuAnTuThien[ maDuAn=" + maDuAn + " ]";
    }

    
    public boolean getIsApproved() {
        return daDuyet == 1;
    }

    public void setIsApproved(boolean daDuyet) {
        this.daDuyet = (short) (daDuyet ? 1 : 0);
    }

    public Short getDaDuyet() {
        return daDuyet;
    }

    public void setDaDuyet(Short daDuyet) {
        this.daDuyet = daDuyet;
    }

    public List<HinhAnhDuAn> getHinhAnhDuAnList() {
        return hinhAnhDuAnList;
    }

    public void setHinhAnhDuAnList(List<HinhAnhDuAn> hinhAnhDuAnList) {
        this.hinhAnhDuAnList = hinhAnhDuAnList;
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

    /**
     * @return the tvBinhLuanDaSet
     */
    public Set<TvBinhLuanDa> getTvBinhLuanDaSet() {
        return tvBinhLuanDaSet;
    }

    /**
     * @param tvBinhLuanDaSet the tvBinhLuanDaSet to set
     */
    public void setTvBinhLuanDaSet(Set<TvBinhLuanDa> tvBinhLuanDaSet) {
        this.tvBinhLuanDaSet = tvBinhLuanDaSet;
    }

}
