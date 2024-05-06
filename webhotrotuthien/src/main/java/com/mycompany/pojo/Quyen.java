/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author vuongthai1205
 */
@Entity
@Table(name = "quyen")
@NamedQueries({
    @NamedQuery(name = "Quyen.findAll", query = "SELECT q FROM Quyen q")})
public class Quyen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MaQuyen")
    private Integer maQuyen;
    @Size(max = 45)
    @Column(name = "TenQuyen")
    private String tenQuyen;
    
    @ManyToMany(mappedBy = "quyenSet", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<ThanhVien> thanhVienSet=new HashSet<>();

    public Quyen() {
    }

    public Quyen(Integer maQuyen) {
        this.maQuyen = maQuyen;
    }

    public Integer getMaQuyen() {
        return maQuyen;
    }

    public void setMaQuyen(Integer maQuyen) {
        this.maQuyen = maQuyen;
    }

    public String getTenQuyen() {
        return tenQuyen;
    }

    public void setTenQuyen(String tenQuyen) {
        this.tenQuyen = tenQuyen;
    }

    public Set<ThanhVien> getThanhVienSet() {
        return thanhVienSet;
    }

    public void setThanhVienSet(Set<ThanhVien> thanhVienSet) {
        this.thanhVienSet = thanhVienSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maQuyen != null ? maQuyen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Quyen)) {
            return false;
        }
        Quyen other = (Quyen) object;
        if ((this.maQuyen == null && other.maQuyen != null) || (this.maQuyen != null && !this.maQuyen.equals(other.maQuyen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.pojo.Quyen[ maQuyen=" + maQuyen + " ]";
    }
    
}
