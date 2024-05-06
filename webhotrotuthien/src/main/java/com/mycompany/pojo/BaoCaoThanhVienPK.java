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
import javax.validation.constraints.Size;

/**
 *
 * @author vuongthai1205
 */
@Embeddable
public class BaoCaoThanhVienPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "MaThanhVienBC")
    private int maThanhVienBC;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MaThanhVienBiBaoCao")
    private int maThanhVienBiBaoCao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "LyDoBaoCao")
    private String lyDoBaoCao;

    public BaoCaoThanhVienPK() {
    }

    public BaoCaoThanhVienPK(int maThanhVienBC, int maThanhVienBiBaoCao, String lyDoBaoCao) {
        this.maThanhVienBC = maThanhVienBC;
        this.maThanhVienBiBaoCao = maThanhVienBiBaoCao;
        this.lyDoBaoCao = lyDoBaoCao;
    }

    public int getMaThanhVienBC() {
        return maThanhVienBC;
    }

    public void setMaThanhVienBC(int maThanhVienBC) {
        this.maThanhVienBC = maThanhVienBC;
    }

    public int getMaThanhVienBiBaoCao() {
        return maThanhVienBiBaoCao;
    }

    public void setMaThanhVienBiBaoCao(int maThanhVienBiBaoCao) {
        this.maThanhVienBiBaoCao = maThanhVienBiBaoCao;
    }

    public String getLyDoBaoCao() {
        return lyDoBaoCao;
    }

    public void setLyDoBaoCao(String lyDoBaoCao) {
        this.lyDoBaoCao = lyDoBaoCao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) maThanhVienBC;
        hash += (int) maThanhVienBiBaoCao;
        hash += (lyDoBaoCao != null ? lyDoBaoCao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BaoCaoThanhVienPK)) {
            return false;
        }
        BaoCaoThanhVienPK other = (BaoCaoThanhVienPK) object;
        if (this.maThanhVienBC != other.maThanhVienBC) {
            return false;
        }
        if (this.maThanhVienBiBaoCao != other.maThanhVienBiBaoCao) {
            return false;
        }
        if ((this.lyDoBaoCao == null && other.lyDoBaoCao != null) || (this.lyDoBaoCao != null && !this.lyDoBaoCao.equals(other.lyDoBaoCao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.pojo.BaoCaoThanhVienPK[ maThanhVienBC=" + maThanhVienBC + ", maThanhVienBiBaoCao=" + maThanhVienBiBaoCao + ", lyDoBaoCao=" + lyDoBaoCao + " ]";
    }
    
}
