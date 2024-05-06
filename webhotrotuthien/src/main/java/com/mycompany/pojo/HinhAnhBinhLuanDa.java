/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vuong
 */
@Entity
@Table(name = "hinh_anh_binh_luan_da")
public class HinhAnhBinhLuanDa implements Serializable {

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
    @Column(name = "MaHinhAnhBinhLuan")
    private Integer maHinhAnhBinhLuan;
    @Size(max = 200)
    @Column(name = "DuongDanHinh")
    private String duongDanHinh;
    @Column(name = "NgayTao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayTao;
    @Column(name = "NgayCapNhat")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayCapNhat;
    @JoinColumn(name = "MaBinhLuan", referencedColumnName = "MaBinhLuan")
    @ManyToOne
    private TvBinhLuanDa maBinhLuan;

    public HinhAnhBinhLuanDa() {
    }

    public HinhAnhBinhLuanDa(Integer maHinhAnhBinhLuan) {
        this.maHinhAnhBinhLuan = maHinhAnhBinhLuan;
    }

    public Integer getMaHinhAnhBinhLuan() {
        return maHinhAnhBinhLuan;
    }

    public void setMaHinhAnhBinhLuan(Integer maHinhAnhBinhLuan) {
        this.maHinhAnhBinhLuan = maHinhAnhBinhLuan;
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

    public TvBinhLuanDa getMaBinhLuan() {
        return maBinhLuan;
    }

    public void setMaBinhLuan(TvBinhLuanDa maBinhLuan) {
        this.maBinhLuan = maBinhLuan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maHinhAnhBinhLuan != null ? maHinhAnhBinhLuan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HinhAnhBinhLuanDa)) {
            return false;
        }
        HinhAnhBinhLuanDa other = (HinhAnhBinhLuanDa) object;
        if ((this.maHinhAnhBinhLuan == null && other.maHinhAnhBinhLuan != null) || (this.maHinhAnhBinhLuan != null && !this.maHinhAnhBinhLuan.equals(other.maHinhAnhBinhLuan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.pojo.HinhAnhBinhLuanDa[ maHinhAnhBinhLuan=" + maHinhAnhBinhLuan + " ]";
    }

}
