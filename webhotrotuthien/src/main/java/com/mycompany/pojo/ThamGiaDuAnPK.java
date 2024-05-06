/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pojo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author vuongthai1205
 */
@Embeddable
public class ThamGiaDuAnPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "MaThanhVien")
    private int maThanhVien;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MaDuAn")
    private int maDuAn;

    public ThamGiaDuAnPK() {
    }

    public ThamGiaDuAnPK(int maThanhVien, int maDuAn) {
        this.maThanhVien = maThanhVien;
        this.maDuAn = maDuAn;
    }

    public int getMaThanhVien() {
        return maThanhVien;
    }

    public void setMaThanhVien(int maThanhVien) {
        this.maThanhVien = maThanhVien;
    }

    public int getMaDuAn() {
        return maDuAn;
    }

    public void setMaDuAn(int maDuAn) {
        this.maDuAn = maDuAn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) maThanhVien;
        hash += (int) maDuAn;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ThamGiaDuAnPK)) {
            return false;
        }
        ThamGiaDuAnPK other = (ThamGiaDuAnPK) object;
        if (this.maThanhVien != other.maThanhVien) {
            return false;
        }
        if (this.maDuAn != other.maDuAn) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.pojo.ThamGiaDuAnPK[ maThanhVien=" + maThanhVien + ", maDuAn=" + maDuAn + " ]";
    }
    
}
