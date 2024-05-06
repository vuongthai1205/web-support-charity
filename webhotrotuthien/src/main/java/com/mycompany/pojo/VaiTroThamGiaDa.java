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
@Table(name = "vai_tro_tham_gia_da")
@NamedQueries({
    @NamedQuery(name = "VaiTroThamGiaDa.findAll", query = "SELECT v FROM VaiTroThamGiaDa v")})
public class VaiTroThamGiaDa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MaVaiTroThamGiaDA")
    private Integer maVaiTroThamGiaDA;
    @Size(max = 45)
    @Column(name = "TenVaiTro")
    private String tenVaiTro;
    @OneToMany(mappedBy = "maVaiTroThamGiaDA")
    private Set<ThamGiaDuAn> thamGiaDuAnSet;

    public VaiTroThamGiaDa() {
    }

    public VaiTroThamGiaDa(Integer maVaiTroThamGiaDA) {
        this.maVaiTroThamGiaDA = maVaiTroThamGiaDA;
    }

    public Integer getMaVaiTroThamGiaDA() {
        return maVaiTroThamGiaDA;
    }

    public void setMaVaiTroThamGiaDA(Integer maVaiTroThamGiaDA) {
        this.maVaiTroThamGiaDA = maVaiTroThamGiaDA;
    }

    public String getTenVaiTro() {
        return tenVaiTro;
    }

    public void setTenVaiTro(String tenVaiTro) {
        this.tenVaiTro = tenVaiTro;
    }

    public Set<ThamGiaDuAn> getThamGiaDuAnSet() {
        return thamGiaDuAnSet;
    }

    public void setThamGiaDuAnSet(Set<ThamGiaDuAn> thamGiaDuAnSet) {
        this.thamGiaDuAnSet = thamGiaDuAnSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maVaiTroThamGiaDA != null ? maVaiTroThamGiaDA.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VaiTroThamGiaDa)) {
            return false;
        }
        VaiTroThamGiaDa other = (VaiTroThamGiaDa) object;
        if ((this.maVaiTroThamGiaDA == null && other.maVaiTroThamGiaDA != null) || (this.maVaiTroThamGiaDA != null && !this.maVaiTroThamGiaDA.equals(other.maVaiTroThamGiaDA))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.pojo.VaiTroThamGiaDa[ maVaiTroThamGiaDA=" + maVaiTroThamGiaDA + " ]";
    }
    
}
