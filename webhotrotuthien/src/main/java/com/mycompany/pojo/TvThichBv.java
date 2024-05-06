/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
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
@Table(name = "tv_thich_bv")
@NamedQueries({
    @NamedQuery(name = "TvThichBv.findAll", query = "SELECT t FROM TvThichBv t")})
public class TvThichBv implements Serializable {
    
     @PrePersist
    protected void onCreate(){
        this.ngayTao=new Date(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate(){
        this.ngayCapNhat=new Date(System.currentTimeMillis());
    }

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TvThichBvPK tvThichBvPK;
    @Column(name = "DaThich")
    private short daThich;
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

    public TvThichBv() {
    }

    public TvThichBv(TvThichBvPK tvThichBvPK) {
        this.tvThichBvPK = tvThichBvPK;
    }

    public TvThichBv(int maThichBaiViet, int maThanhVien) {
        this.tvThichBvPK = new TvThichBvPK(maThichBaiViet, maThanhVien);
    }

    public TvThichBvPK getTvThichBvPK() {
        return tvThichBvPK;
    }

    public void setTvThichBvPK(TvThichBvPK tvThichBvPK) {
        this.tvThichBvPK = tvThichBvPK;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tvThichBvPK != null ? tvThichBvPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TvThichBv)) {
            return false;
        }
        TvThichBv other = (TvThichBv) object;
        if ((this.tvThichBvPK == null && other.tvThichBvPK != null) || (this.tvThichBvPK != null && !this.tvThichBvPK.equals(other.tvThichBvPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.pojo.TvThichBv[ tvThichBvPK=" + tvThichBvPK + " ]";
    }

    /**
     * @return the baiViet
     */
    public BaiViet getBaiViet() {
        return baiViet;
    }

    /**
     * @param baiViet the baiViet to set
     */
    public void setBaiViet(BaiViet baiViet) {
        this.baiViet = baiViet;
    }

    /**
     * @return the daThich
     */
    public short getDaThich() {
        return daThich;
    }

    /**
     * @param daThich the daThich to set
     */
    public void setDaThich(short daThich) {
        this.daThich = daThich;
    }
    
    public boolean isIsLike() {
        return daThich == 1;
    }

    public void setIsLike(boolean daThich) {
        this.daThich = (short) (daThich ? 1 : 0);
    }
    
}
