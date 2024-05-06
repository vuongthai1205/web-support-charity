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

/**
 *
 * @author vuongthai1205
 */
@Entity
@Table(name = "bao_cao_thanh_vien")
@NamedQueries({
    @NamedQuery(name = "BaoCaoThanhVien.findAll", query = "SELECT b FROM BaoCaoThanhVien b")})
public class BaoCaoThanhVien implements Serializable {
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
    protected BaoCaoThanhVienPK baoCaoThanhVienPK;
    @Column(name = "NgayTao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayTao;
    @Column(name = "NgayCapNhat")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayCapNhat;
    @JoinColumn(name = "MaThanhVienBC", referencedColumnName = "MaThanhVien", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ThanhVien thanhVien;
    @JoinColumn(name = "MaThanhVienBiBaoCao", referencedColumnName = "MaThanhVien", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ThanhVien thanhVien1;

    public BaoCaoThanhVien() {
    }

    public BaoCaoThanhVien(BaoCaoThanhVienPK baoCaoThanhVienPK) {
        this.baoCaoThanhVienPK = baoCaoThanhVienPK;
    }

    public BaoCaoThanhVien(int maThanhVienBC, int maThanhVienBiBaoCao, String lyDoBaoCao) {
        this.baoCaoThanhVienPK = new BaoCaoThanhVienPK(maThanhVienBC, maThanhVienBiBaoCao, lyDoBaoCao);
    }

    public BaoCaoThanhVienPK getBaoCaoThanhVienPK() {
        return baoCaoThanhVienPK;
    }

    public void setBaoCaoThanhVienPK(BaoCaoThanhVienPK baoCaoThanhVienPK) {
        this.baoCaoThanhVienPK = baoCaoThanhVienPK;
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

    public ThanhVien getThanhVien() {
        return thanhVien;
    }

    public void setThanhVien(ThanhVien thanhVien) {
        this.thanhVien = thanhVien;
    }

    public ThanhVien getThanhVien1() {
        return thanhVien1;
    }

    public void setThanhVien1(ThanhVien thanhVien1) {
        this.thanhVien1 = thanhVien1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (baoCaoThanhVienPK != null ? baoCaoThanhVienPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BaoCaoThanhVien)) {
            return false;
        }
        BaoCaoThanhVien other = (BaoCaoThanhVien) object;
        if ((this.baoCaoThanhVienPK == null && other.baoCaoThanhVienPK != null) || (this.baoCaoThanhVienPK != null && !this.baoCaoThanhVienPK.equals(other.baoCaoThanhVienPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.pojo.BaoCaoThanhVien[ baoCaoThanhVienPK=" + baoCaoThanhVienPK + " ]";
    }
    
}
