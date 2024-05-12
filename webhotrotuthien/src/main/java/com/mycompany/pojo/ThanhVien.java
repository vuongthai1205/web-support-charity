/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.googlecode.jmapper.annotations.JMap;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author vuongthai1205
 */
@Entity
@Table(name = "thanh_vien")
@NamedQueries({
    @NamedQuery(name = "ThanhVien.findAll", query = "SELECT t FROM ThanhVien t")})
public class ThanhVien implements UserDetails {
    

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MaThanhVien")
    @JMap("id")
    private Integer maThanhVien;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45, message = "{user.username.notNullMsg}")
    @Column(name = "TenDangNhap", unique = true)
    @JMap("username")
    private String tenDangNhap;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "MatKhau")
    private String matKhau;
    @Size(max = 12)
    @Column(name = "SoDienThoai")
    @JMap("phone")
    private String soDienThoai;
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "email")
    @JMap("email")
    private String email;
    @Size(max = 45)
    @Column(name = "Ho")
    @JMap("lastName")
    private String ho;
    @Size(max = 45)
    @Column(name = "Ten")
    @JMap("firstName")
    private String ten;
    @Column(name = "Tuoi")
    private Integer tuoi;
    @Column(name = "GioiTinh")
    @JMap("gender")
    private Short gioiTinh;
    @Column(name = "NgaySinh")
    @Temporal(TemporalType.TIMESTAMP)
    @JMap("dateOfBirth")
    private Date ngaySinh;
    @Size(max = 100)
    @Column(name = "DiaChi")
    @JMap("address")
    private String diaChi;
    @Size(max = 200)
    @Column(name = "AnhDaiDien")
    @JMap("avatar")
    private String anhDaiDien;
    @Column(name = "TongTien")
    private Double tongTien;
    @Column(name = "NgayTao")
    @Temporal(TemporalType.TIMESTAMP)
    @JMap("createAt")
    private Date ngayTao;
    @Column(name = "NgayCapNhat")
    @Temporal(TemporalType.TIMESTAMP)
    @JMap("updateAt")
    private Date ngayCapNhat;
    @JoinTable(name = "quyen_cua_thanh_vien", joinColumns = {
        @JoinColumn(name = "MaThanhVien")}, inverseJoinColumns = {
        @JoinColumn(name = "MaQuyen")})
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Quyen> quyenSet= new HashSet<>();;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "thanhVien")
    @JsonIgnore
    private Set<ThamGiaDuAn> thamGiaDuAnSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "thanhVien")
    @JsonIgnore
    private Set<DauGia> dauGiaSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "thanhVien")
    @JsonIgnore
    private Set<TvBinhLuanBv> tvBinhLuanBvSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "thanhVien")
    @JsonIgnore
    private Set<TvBinhLuanDa> tvBinhLuanDaSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "thanhVien")
    @JsonIgnore
    private Set<TvThichBv> tvThichBvSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maThanhVien")
    @JsonIgnore
    private Set<BaiViet> baiVietSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maThanhVienTaoDA")
    @JsonIgnore
    private Set<DuAnTuThien> duAnTuThienSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "thanhVien")
    @JsonIgnore
    private Set<BaoCaoThanhVien> baoCaoThanhVienSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "thanhVien1")
    @JsonIgnore
    private Set<BaoCaoThanhVien> baoCaoThanhVienSet1;
    @JsonIgnore
    @OneToMany(mappedBy = "maThanhVien")
    private Collection<PhieuYeuCau> phieuYeuCauCollection;
    
    @Transient
    @JsonIgnore
    private MultipartFile file;
    
    @Transient
    @JsonIgnore
    private String dateString;
    
    @Transient
    @JsonIgnore
    private Quyen role;
    
    @Transient
    @JsonIgnore
    private String repeatPassword;
    
    @Transient
    @JsonIgnore
    private String fakePassword;

    public ThanhVien() {
    }

    public ThanhVien(Integer maThanhVien) {
        this.maThanhVien = maThanhVien;
    }
    
    public void addRole(Quyen role) {
        this.quyenSet.add(role);
        role.getThanhVienSet().add(this);
    }

    public ThanhVien(Integer maThanhVien, String tenDangNhap, String matKhau) {
        this.maThanhVien = maThanhVien;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
    }

    public Integer getMaThanhVien() {
        return maThanhVien;
    }

    public void setMaThanhVien(Integer maThanhVien) {
        this.maThanhVien = maThanhVien;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Integer getTuoi() {
        return tuoi;
    }

    public void setTuoi(Integer tuoi) {
        this.tuoi = tuoi;
    }

    public Short getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(Short gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getAnhDaiDien() {
        return anhDaiDien;
    }

    public void setAnhDaiDien(String anhDaiDien) {
        this.anhDaiDien = anhDaiDien;
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

    public Set<Quyen> getQuyenSet() {
        return quyenSet;
    }

    public void setQuyenSet(Set<Quyen> quyenSet) {
        this.quyenSet = quyenSet;
    }

    public Set<ThamGiaDuAn> getThamGiaDuAnSet() {
        return thamGiaDuAnSet;
    }

    public void setThamGiaDuAnSet(Set<ThamGiaDuAn> thamGiaDuAnSet) {
        this.thamGiaDuAnSet = thamGiaDuAnSet;
    }

    public Set<DauGia> getDauGiaSet() {
        return dauGiaSet;
    }

    public void setDauGiaSet(Set<DauGia> dauGiaSet) {
        this.dauGiaSet = dauGiaSet;
    }

    public Set<TvBinhLuanBv> getTvBinhLuanBvSet() {
        return tvBinhLuanBvSet;
    }

    public void setTvBinhLuanBvSet(Set<TvBinhLuanBv> tvBinhLuanBvSet) {
        this.tvBinhLuanBvSet = tvBinhLuanBvSet;
    }

    public Set<TvThichBv> getTvThichBvSet() {
        return tvThichBvSet;
    }

    public void setTvThichBvSet(Set<TvThichBv> tvThichBvSet) {
        this.tvThichBvSet = tvThichBvSet;
    }

    public Set<BaiViet> getBaiVietSet() {
        return baiVietSet;
    }

    public void setBaiVietSet(Set<BaiViet> baiVietSet) {
        this.baiVietSet = baiVietSet;
    }

    public Set<DuAnTuThien> getDuAnTuThienSet() {
        return duAnTuThienSet;
    }

    public void setDuAnTuThienSet(Set<DuAnTuThien> duAnTuThienSet) {
        this.duAnTuThienSet = duAnTuThienSet;
    }

    public Set<BaoCaoThanhVien> getBaoCaoThanhVienSet() {
        return baoCaoThanhVienSet;
    }

    public void setBaoCaoThanhVienSet(Set<BaoCaoThanhVien> baoCaoThanhVienSet) {
        this.baoCaoThanhVienSet = baoCaoThanhVienSet;
    }

    public Set<BaoCaoThanhVien> getBaoCaoThanhVienSet1() {
        return baoCaoThanhVienSet1;
    }

    public void setBaoCaoThanhVienSet1(Set<BaoCaoThanhVien> baoCaoThanhVienSet1) {
        this.baoCaoThanhVienSet1 = baoCaoThanhVienSet1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maThanhVien != null ? maThanhVien.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ThanhVien)) {
            return false;
        }
        ThanhVien other = (ThanhVien) object;
        if ((this.maThanhVien == null && other.maThanhVien != null) || (this.maThanhVien != null && !this.maThanhVien.equals(other.maThanhVien))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.pojo.ThanhVien[ maThanhVien=" + maThanhVien + " ]";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authorities=new ArrayList<>();
        quyenSet.stream().forEach(i->authorities.add(new SimpleGrantedAuthority(i.getTenQuyen())));
        return List.of(new SimpleGrantedAuthority(authorities.toString()));
    }

    @Override
    public String getUsername() {
        return tenDangNhap;
    }
    
    @Override
    public String getPassword() {
        return matKhau;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;}

    @Override
    public boolean isCredentialsNonExpired() {
        return true;}

    @Override
    public boolean isEnabled() {
        return true;}

    

    /**
     * @return the dateString
     */
    public String getDateString() {
        return dateString;
    }

    /**
     * @param dateString the dateString to set
     */
    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    /**
     * @return the role
     */
    public Quyen getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(Quyen role) {
        this.role = role;
    }

    /**
     * @return the repeatPassword
     */
    public String getRepeatPassword() {
        return repeatPassword;
    }

    /**
     * @param repeatPassword the repeatPassword to set
     */
    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    /**
     * @return the file
     */
    public MultipartFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(MultipartFile file) {
        this.file = file;
    }

    /**
     * @return the tvBinhLuanDaSet
     */
    public Set<TvBinhLuanDa> getTvBinhLuanDaSet() {
        return tvBinhLuanDaSet;
    }

    /**
     * @param tvBinhLuanDaSet the tvBinhLuanDaSet to set
     */
    public void setTvBinhLuanDaSet(Set<TvBinhLuanDa> tvBinhLuanDaSet) {
        this.tvBinhLuanDaSet = tvBinhLuanDaSet;
    }

    /**
     * @return the fakePassword
     */
    public String getFakePassword() {
        return fakePassword;
    }

    /**
     * @param fakePassword the fakePassword to set
     */
    public void setFakePassword(String fakePassword) {
        this.fakePassword = fakePassword;
    }


    @XmlTransient
    public Collection<PhieuYeuCau> getPhieuYeuCauCollection() {
        return phieuYeuCauCollection;
    }

    public void setPhieuYeuCauCollection(Collection<PhieuYeuCau> phieuYeuCauCollection) {
        this.phieuYeuCauCollection = phieuYeuCauCollection;
    }

    /**
     * @return the tongTien
     */
    public Double getTongTien() {
        return tongTien;
    }

    /**
     * @param tongTien the tongTien to set
     */
    public void setTongTien(Double tongTien) {
        this.tongTien = tongTien;
    }

}
