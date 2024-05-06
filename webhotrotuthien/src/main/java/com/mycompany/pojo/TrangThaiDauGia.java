/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pojo;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author vuongthai1205
 */
@Entity
@Table(name = "trang_thai_dau_gia")
@NamedQueries({
    @NamedQuery(name = "TrangThaiDauGia.findAll", query = "SELECT t FROM TrangThaiDauGia t")})
public class TrangThaiDauGia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MaTrangThaiDauGia")
    private Integer maTrangThaiDauGia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "TenTrangThai")
    private String tenTrangThai;
    @OneToMany(mappedBy = "trangThaiDauGia")
    private Set<BaiViet> baiVietSet;

    public TrangThaiDauGia() {
    }

    public TrangThaiDauGia(Integer maTrangThaiDauGia) {
        this.maTrangThaiDauGia = maTrangThaiDauGia;
    }

    public TrangThaiDauGia(Integer maTrangThaiDauGia, String tenTrangThai) {
        this.maTrangThaiDauGia = maTrangThaiDauGia;
        this.tenTrangThai = tenTrangThai;
    }

    public Integer getMaTrangThaiDauGia() {
        return maTrangThaiDauGia;
    }

    public void setMaTrangThaiDauGia(Integer maTrangThaiDauGia) {
        this.maTrangThaiDauGia = maTrangThaiDauGia;
    }

    public String getTenTrangThai() {
        return tenTrangThai;
    }

    public void setTenTrangThai(String tenTrangThai) {
        this.tenTrangThai = tenTrangThai;
    }

    public Set<BaiViet> getBaiVietSet() {
        return baiVietSet;
    }

    public void setBaiVietSet(Set<BaiViet> baiVietSet) {
        this.baiVietSet = baiVietSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maTrangThaiDauGia != null ? maTrangThaiDauGia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TrangThaiDauGia)) {
            return false;
        }
        TrangThaiDauGia other = (TrangThaiDauGia) object;
        if ((this.maTrangThaiDauGia == null && other.maTrangThaiDauGia != null) || (this.maTrangThaiDauGia != null && !this.maTrangThaiDauGia.equals(other.maTrangThaiDauGia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.pojo.TrangThaiDauGia[ maTrangThaiDauGia=" + maTrangThaiDauGia + " ]";
    }
    
}
