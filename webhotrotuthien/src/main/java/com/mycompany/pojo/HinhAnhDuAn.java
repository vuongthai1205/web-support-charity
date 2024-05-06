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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author vuongthai1205
 */
@Entity
@Table(name = "hinh_anh_du_an")
@NamedQueries({
    @NamedQuery(name = "HinhAnhDuAn.findAll", query = "SELECT h FROM HinhAnhDuAn h")})
public class HinhAnhDuAn implements Serializable {
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
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaHinhDuAn")
    private Integer maHinhDuAn;
    @Size(max = 200)
    @Column(name = "DuongDanHinh")
    private String duongDanHinh;
    @Column(name = "NgayTao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayTao;
    @Column(name = "NgayCapNhat")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayCapNhat;
    @JoinColumn(name = "MaDuAn", referencedColumnName = "MaDuAn")
    @ManyToOne
    private DuAnTuThien duAn;

    public HinhAnhDuAn() {
    }

    public HinhAnhDuAn(Integer maHinhDuAn) {
        this.maHinhDuAn = maHinhDuAn;
    }

    public Integer getMaHinhDuAn() {
        return maHinhDuAn;
    }

    public void setMaHinhDuAn(Integer maHinhDuAn) {
        this.maHinhDuAn = maHinhDuAn;
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

    public DuAnTuThien getMaDuAn() {
        return duAn;
    }

    public void setMaDuAn(DuAnTuThien duAn) {
        this.duAn = duAn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maHinhDuAn != null ? maHinhDuAn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HinhAnhDuAn)) {
            return false;
        }
        HinhAnhDuAn other = (HinhAnhDuAn) object;
        if ((this.maHinhDuAn == null && other.maHinhDuAn != null) || (this.maHinhDuAn != null && !this.maHinhDuAn.equals(other.maHinhDuAn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.pojo.HinhAnhDuAn[ maHinhDuAn=" + maHinhDuAn + " ]";
    }
    
}
