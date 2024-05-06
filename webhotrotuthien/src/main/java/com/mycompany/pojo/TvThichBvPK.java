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
public class TvThichBvPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "MaBaiViet")
    private int maBaiViet;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MaThanhVien")
    private int maThanhVien;

    public TvThichBvPK() {
    }

    public TvThichBvPK(int maThichBaiViet, int maThanhVien) {
        this.maBaiViet = maThichBaiViet;
        this.maThanhVien = maThanhVien;
    }

    public int getMaBaiViet() {
        return maBaiViet;
    }

    public void setMaBaiViet(int maBaiViet) {
        this.maBaiViet = maBaiViet;
    }

    public int getMaThanhVien() {
        return maThanhVien;
    }

    public void setMaThanhVien(int maThanhVien) {
        this.maThanhVien = maThanhVien;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) maBaiViet;
        hash += (int) maThanhVien;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TvThichBvPK)) {
            return false;
        }
        TvThichBvPK other = (TvThichBvPK) object;
        if (this.maBaiViet != other.maBaiViet) {
            return false;
        }
        if (this.maThanhVien != other.maThanhVien) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.pojo.TvThichBvPK[ maThichBaiViet=" + maBaiViet + ", maThanhVien=" + maThanhVien + " ]";
    }
    
}
