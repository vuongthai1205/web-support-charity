/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author vuongthai1205
 */
@Embeddable
public class DauGiaPK implements Serializable {

    

    
    @Basic(optional = false)
    @NotNull
    @Column(name = "MaThanhVien")
    private int maThanhVien;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MaBaiViet")
    private int maBaiViet;

    public DauGiaPK() {
    }

    public DauGiaPK(int maThanhVien, int maBaiViet) {
        this.maThanhVien = maThanhVien;
        this.maBaiViet = maBaiViet;
    }

    public int getMaThanhVien() {
        return maThanhVien;
    }

    public void setMaThanhVien(int maThanhVien) {
        this.maThanhVien = maThanhVien;
    }

    public int getMaBaiViet() {
        return maBaiViet;
    }

    public void setMaBaiViet(int maBaiViet) {
        this.maBaiViet = maBaiViet;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) maThanhVien;
        hash += (int) maBaiViet;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DauGiaPK)) {
            return false;
        }
        DauGiaPK other = (DauGiaPK) object;
        if (this.maThanhVien != other.maThanhVien) {
            return false;
        }
        if (this.maBaiViet != other.maBaiViet) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.pojo.DauGiaPK[ maThanhVien=" + maThanhVien + ", maBaiViet=" + maBaiViet + "  ]";
    }

}
