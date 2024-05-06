/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
@Table(name = "tham_gia_du_an")
@NamedQueries({
    @NamedQuery(name = "ThamGiaDuAn.findAll", query = "SELECT t FROM ThamGiaDuAn t")})
public class ThamGiaDuAn implements Serializable {

    @PrePersist
    protected void onCreate() {
        this.ngayTao = new Date(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate() {
        this.ngayCapNhat = new Date(System.currentTimeMillis());
    }
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ThamGiaDuAnPK thamGiaDuAnPK;
    @Column(name = "NgayThamGia")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayThamGia;
    @Size(max = 200)
    @Column(name = "CacDongGopKhac")
    private String cacDongGopKhac;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SoTienDongGop")
    private Double soTienDongGop;
    @Column(name = "NgayTao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayTao;
    @Column(name = "NgayCapNhat")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayCapNhat;
    @JoinColumn(name = "MaDuAn", referencedColumnName = "MaDuAn", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DuAnTuThien duAnTuThien;
    @JoinColumn(name = "MaThanhVien", referencedColumnName = "MaThanhVien", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ThanhVien thanhVien;
    @JoinColumn(name = "MaVaiTroThamGiaDA", referencedColumnName = "MaVaiTroThamGiaDA")
    @ManyToOne
    private VaiTroThamGiaDa maVaiTroThamGiaDA;

    public ThamGiaDuAn() {
    }

    public ThamGiaDuAn(ThamGiaDuAnPK thamGiaDuAnPK) {
        this.thamGiaDuAnPK = thamGiaDuAnPK;
    }

    public ThamGiaDuAn(int maThanhVien, int maDuAn) {
        this.thamGiaDuAnPK = new ThamGiaDuAnPK(maThanhVien, maDuAn);
    }

    public ThamGiaDuAnPK getThamGiaDuAnPK() {
        return thamGiaDuAnPK;
    }

    public void setThamGiaDuAnPK(ThamGiaDuAnPK thamGiaDuAnPK) {
        this.thamGiaDuAnPK = thamGiaDuAnPK;
    }

    public Date getNgayThamGia() {
        return ngayThamGia;
    }

    public void setNgayThamGia(Date ngayThamGia) {
        this.ngayThamGia = ngayThamGia;
    }

    public String getCacDongGopKhac() {
        return cacDongGopKhac;
    }

    public void setCacDongGopKhac(String cacDongGopKhac) {
        this.cacDongGopKhac = cacDongGopKhac;
    }

    public Double getSoTienDongGop() {
        return soTienDongGop;
    }

    public void setSoTienDongGop(Double soTienDongGop) {
        this.soTienDongGop = soTienDongGop;
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

    public DuAnTuThien getDuAnTuThien() {
        return duAnTuThien;
    }

    public void setDuAnTuThien(DuAnTuThien duAnTuThien) {
        this.duAnTuThien = duAnTuThien;
    }

    public ThanhVien getThanhVien() {
        return thanhVien;
    }

    public void setThanhVien(ThanhVien thanhVien) {
        this.thanhVien = thanhVien;
    }

    public VaiTroThamGiaDa getMaVaiTroThamGiaDA() {
        return maVaiTroThamGiaDA;
    }

    public void setMaVaiTroThamGiaDA(VaiTroThamGiaDa maVaiTroThamGiaDA) {
        this.maVaiTroThamGiaDA = maVaiTroThamGiaDA;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (thamGiaDuAnPK != null ? thamGiaDuAnPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ThamGiaDuAn)) {
            return false;
        }
        ThamGiaDuAn other = (ThamGiaDuAn) object;
        if ((this.thamGiaDuAnPK == null && other.thamGiaDuAnPK != null) || (this.thamGiaDuAnPK != null && !this.thamGiaDuAnPK.equals(other.thamGiaDuAnPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.pojo.ThamGiaDuAn[ thamGiaDuAnPK=" + thamGiaDuAnPK + " ]";
    }

}
