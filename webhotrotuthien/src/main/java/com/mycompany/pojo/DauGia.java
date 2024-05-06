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
@Table(name = "dau_gia")
@NamedQueries({
    @NamedQuery(name = "DauGia.findAll", query = "SELECT d FROM DauGia d")})
public class DauGia implements Serializable {

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
    protected DauGiaPK dauGiaPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "GiaTien")
    private Double giaTien;
    @Column(name = "DaThangDauGia")
    private short daThangDauGia;
    @Column(name = "NgayTao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayTao;
    @Column(name = "NgayCapNhat")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayCapNhat;
    @JoinColumn(name = "MaBaiViet", referencedColumnName = "MaBaiViet", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private BaiViet baiViet;
    @JoinColumn(name = "MaThanhVien", referencedColumnName = "MaThanhVien", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ThanhVien thanhVien;

    public DauGia() {
    }

    public DauGia(DauGiaPK dauGiaPK) {
        this.dauGiaPK = dauGiaPK;
    }

    public DauGia(int maThanhVien, int maBaiViet) {
        this.dauGiaPK = new DauGiaPK(maThanhVien, maBaiViet);
    }

    public DauGiaPK getDauGiaPK() {
        return dauGiaPK;
    }

    public void setDauGiaPK(DauGiaPK dauGiaPK) {
        this.dauGiaPK = dauGiaPK;
    }

    public Double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(Double giaTien) {
        this.giaTien = giaTien;
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

    public ThanhVien getThanhVien() {
        return thanhVien;
    }

    public void setThanhVien(ThanhVien thanhVien) {
        this.thanhVien = thanhVien;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dauGiaPK != null ? dauGiaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DauGia)) {
            return false;
        }
        DauGia other = (DauGia) object;
        if ((this.dauGiaPK == null && other.dauGiaPK != null) || (this.dauGiaPK != null && !this.dauGiaPK.equals(other.dauGiaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.pojo.DauGia[ dauGiaPK=" + dauGiaPK + " ]";
    }

    /**
     * @return the daThangDauGia
     */
    public short getDaThangDauGia() {
        return daThangDauGia;
    }

    /**
     * @param daThangDauGia the daThangDauGia to set
     */
    public void setDaThangDauGia(short daThangDauGia) {
        this.daThangDauGia = daThangDauGia;
    }

    public boolean isDaThangDauGia() {
        return daThangDauGia == 1;
    }

    public void setDaThangDauGia(boolean daThangDauGia) {
        this.daThangDauGia = (short) (daThangDauGia ? 1 : 0);
    }
    
    public boolean isWinnerAuction() {
        return daThangDauGia == 1;
    }

    public void setIsWinnerAuction(boolean isWinnerAuction) {
        this.daThangDauGia = (short) (isWinnerAuction ? 1 : 0);
    }
}
