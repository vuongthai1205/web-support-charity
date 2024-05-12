/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pojo;

import com.googlecode.jmapper.annotations.JMap;
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
@Table(name = "phieu_yeu_cau")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PhieuYeuCau.findAll", query = "SELECT p FROM PhieuYeuCau p"),
    @NamedQuery(name = "PhieuYeuCau.findByMaPhieuYeuCau", query = "SELECT p FROM PhieuYeuCau p WHERE p.maPhieuYeuCau = :maPhieuYeuCau"),
    @NamedQuery(name = "PhieuYeuCau.findBySoTien", query = "SELECT p FROM PhieuYeuCau p WHERE p.soTien = :soTien"),
    @NamedQuery(name = "PhieuYeuCau.findByXacNhan", query = "SELECT p FROM PhieuYeuCau p WHERE p.xacNhan = :xacNhan"),
    @NamedQuery(name = "PhieuYeuCau.findByNgayTao", query = "SELECT p FROM PhieuYeuCau p WHERE p.ngayTao = :ngayTao"),
    @NamedQuery(name = "PhieuYeuCau.findByNgayCapNhat", query = "SELECT p FROM PhieuYeuCau p WHERE p.ngayCapNhat = :ngayCapNhat")})
public class PhieuYeuCau implements Serializable {

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
    @Column(name = "MaPhieuYeuCau")
    private Integer maPhieuYeuCau;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SoTien")
    @JMap("moneyMount")
    private Double soTien;
    @Column(name = "XacNhan")
    private Short xacNhan;
    @Size(max = 45)
    @Column(name = "LoaiPhieu")
    @JMap("typeRequest")
    private String loaiPhieu;
    @Column(name = "NgayTao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayTao;
    @Column(name = "NgayCapNhat")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayCapNhat;
    @JoinColumn(name = "MaThanhVien", referencedColumnName = "MaThanhVien")
    @ManyToOne
    private ThanhVien maThanhVien;

    public PhieuYeuCau() {
    }

    public PhieuYeuCau(Integer maPhieuYeuCau) {
        this.maPhieuYeuCau = maPhieuYeuCau;
    }

    public Integer getMaPhieuYeuCau() {
        return maPhieuYeuCau;
    }

    public void setMaPhieuYeuCau(Integer maPhieuYeuCau) {
        this.maPhieuYeuCau = maPhieuYeuCau;
    }

    public Double getSoTien() {
        return soTien;
    }

    public void setSoTien(Double soTien) {
        this.soTien = soTien;
    }

    public Short getXacNhan() {
        return xacNhan;
    }

    public void setXacNhan(Short xacNhan) {
        this.xacNhan = xacNhan;
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

    public ThanhVien getMaThanhVien() {
        return maThanhVien;
    }

    public void setMaThanhVien(ThanhVien maThanhVien) {
        this.maThanhVien = maThanhVien;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maPhieuYeuCau != null ? maPhieuYeuCau.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PhieuYeuCau)) {
            return false;
        }
        PhieuYeuCau other = (PhieuYeuCau) object;
        if ((this.maPhieuYeuCau == null && other.maPhieuYeuCau != null) || (this.maPhieuYeuCau != null && !this.maPhieuYeuCau.equals(other.maPhieuYeuCau))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.pojo.PhieuYeuCau[ maPhieuYeuCau=" + maPhieuYeuCau + " ]";
    }

    /**
     * @return the loaiPhieu
     */
    public String getLoaiPhieu() {
        return loaiPhieu;
    }

    /**
     * @param loaiPhieu the loaiPhieu to set
     */
    public void setLoaiPhieu(String loaiPhieu) {
        this.loaiPhieu = loaiPhieu;
    }

}
