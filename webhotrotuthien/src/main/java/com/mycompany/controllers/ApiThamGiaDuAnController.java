/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controllers;

import com.mycompany.DTO.CharityProjectResponseDTO;
import com.mycompany.DTO.JoinProjectRequestDTO;
import com.mycompany.DTO.JoinProjectResponseDTO;
import com.mycompany.DTO.UserResponseDTO;
import com.mycompany.pojo.DuAnTuThien;
import com.mycompany.pojo.ThamGiaDuAn;
import com.mycompany.pojo.ThamGiaDuAnPK;
import com.mycompany.pojo.ThanhVien;
import com.mycompany.pojo.VaiTroThamGiaDa;
import com.mycompany.service.DuAnTuThienService;
import com.mycompany.service.EmailService;
import com.mycompany.service.ThamGiaDuAnService;
import com.mycompany.service.ThanhVienService;
import com.mycompany.service.VaiTroThamGiaDuAnService;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vuongthai1205
 */
@RestController
@CrossOrigin
@RequestMapping("/api")
public class ApiThamGiaDuAnController {

    @Autowired
    private ThamGiaDuAnService thamGiaDuAnService;
    @Autowired
    private DuAnTuThienService duAnTuThienService;
    @Autowired
    private ThanhVienService thanhVienService;

    @Autowired
    private VaiTroThamGiaDuAnService vaiTroThamGiaDuAnService;
    @Autowired
    private EmailService emailService;

    @PostMapping("/join-project/")
    public ResponseEntity<String> addJoinProject(Principal user, @RequestBody JoinProjectRequestDTO joinProjectRequestDTO) {
        ThanhVien u = this.thanhVienService.getUserByUsername(user.getName());
        DuAnTuThien duAnTuThien = this.duAnTuThienService.getDuAnTuThienById(joinProjectRequestDTO.getIdProject());
        ThamGiaDuAn thamGiaDuAn = new ThamGiaDuAn();

        thamGiaDuAn.setDuAnTuThien(duAnTuThien);
        thamGiaDuAn.setThanhVien(u);
        thamGiaDuAn.setCacDongGopKhac(joinProjectRequestDTO.getContributionOther());
        thamGiaDuAn.setSoTienDongGop(joinProjectRequestDTO.getContributionAmount());
        ThamGiaDuAnPK duAnPK = new ThamGiaDuAnPK(u.getMaThanhVien(), duAnTuThien.getMaDuAn());
        VaiTroThamGiaDa vaiTroThamGiaDa = this.vaiTroThamGiaDuAnService.vaiTroThamGiaDa(3);

        thamGiaDuAn.setMaVaiTroThamGiaDA(vaiTroThamGiaDa);
        thamGiaDuAn.setThamGiaDuAnPK(duAnPK);
        if (this.thamGiaDuAnService.addUserToProject(thamGiaDuAn)) {
            this.emailService.sendSimpleMessage(thamGiaDuAn.getThanhVien().getEmail(), "Thank you for join project",
                    "Thank " + 
                    thamGiaDuAn.getThanhVien().getTen() +
                    ", for participating in " +
                    thamGiaDuAn.getDuAnTuThien().getTenDuAn() +
                    " project");
            return new ResponseEntity<>("oke", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("not oke", HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/join-project/")
    public ResponseEntity<String> updateJoinProject(Principal user, @RequestBody JoinProjectRequestDTO joinProjectRequestDTO) {
        ThanhVien u = this.thanhVienService.getUserByUsername(user.getName());
        DuAnTuThien duAnTuThien = this.duAnTuThienService.getDuAnTuThienById(joinProjectRequestDTO.getIdProject());
        ThanhVien thanhVien = this.thanhVienService.getUserById(joinProjectRequestDTO.getIdUser());
        ThamGiaDuAn thamGiaDuAn = this.thamGiaDuAnService.getThamGiaDuAn(thanhVien, duAnTuThien);
        if (duAnTuThien.getMaThanhVienTaoDA().equals(u)) {
            VaiTroThamGiaDa vaiTroThamGiaDa = this.vaiTroThamGiaDuAnService.vaiTroThamGiaDa(joinProjectRequestDTO.getRole());
            thamGiaDuAn.setMaVaiTroThamGiaDA(vaiTroThamGiaDa);
            if (this.thamGiaDuAnService.updateJoinProject(thamGiaDuAn)) {
                return new ResponseEntity<>("oke", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("not oke", HttpStatus.BAD_REQUEST);
            }
        } else if (thamGiaDuAn.getThanhVien().equals(u)) {
            thamGiaDuAn.setCacDongGopKhac(joinProjectRequestDTO.getContributionOther());
            thamGiaDuAn.setSoTienDongGop(joinProjectRequestDTO.getContributionAmount());
            if (this.thamGiaDuAnService.updateJoinProject(thamGiaDuAn)) {
                return new ResponseEntity<>("oke", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("not oke", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("You can not permission to edit the project", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/join-project/{id-project}/{id-user}")
    public ResponseEntity<String> deleteJoinProject(Principal user, @PathVariable(value = "id-project") int idProject, @PathVariable(value = "id-user") int idUser) {
        ThanhVien u = this.thanhVienService.getUserByUsername(user.getName());
        DuAnTuThien duAnTuThien = this.duAnTuThienService.getDuAnTuThienById(idProject);
        ThanhVien thanhVien = this.thanhVienService.getUserById(idUser);
        ThamGiaDuAn thamGiaDuAn = this.thamGiaDuAnService.getThamGiaDuAn(thanhVien, duAnTuThien);
        if (duAnTuThien.getMaThanhVienTaoDA().equals(u)) {
            if (this.thamGiaDuAnService.deleteJoinProject(thamGiaDuAn)) {
                return new ResponseEntity<>("oke", HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>("not oke", HttpStatus.BAD_REQUEST);
            }
        } else if (thamGiaDuAn.getThanhVien().equals(u)) {
            if (this.thamGiaDuAnService.deleteJoinProject(thamGiaDuAn)) {
                return new ResponseEntity<>("oke", HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>("not oke", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("You can not permission to delete the project", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/join-project/{id}/")
    public ResponseEntity<List<JoinProjectResponseDTO>> getJoinProject(@PathVariable(value = "id") int id) {
        DuAnTuThien duAnTuThien = this.duAnTuThienService.getDuAnTuThienById(id);
        List<ThamGiaDuAn> thamGiaDuAns = this.thamGiaDuAnService.getThamGiaDuAnByDuAn(duAnTuThien);
        List<JoinProjectResponseDTO> joinProjectResponseDTOs = new ArrayList<>();
        thamGiaDuAns.forEach(d -> {
            JoinProjectResponseDTO joinProjectResponseDTO = new JoinProjectResponseDTO();
            UserResponseDTO userResponseDTO = new UserResponseDTO();
            userResponseDTO.setUsername(d.getThanhVien().getUsername());
            userResponseDTO.setAvatar(d.getThanhVien().getAnhDaiDien());
            userResponseDTO.setId(d.getThanhVien().getMaThanhVien());

            UserResponseDTO uResponseDTO = new UserResponseDTO();
            uResponseDTO.setUsername(d.getDuAnTuThien().getMaThanhVienTaoDA().getUsername());
            uResponseDTO.setAvatar(d.getDuAnTuThien().getMaThanhVienTaoDA().getAnhDaiDien());
            uResponseDTO.setId(d.getDuAnTuThien().getMaThanhVienTaoDA().getMaThanhVien());

            CharityProjectResponseDTO charityProjectResponseDTO = new CharityProjectResponseDTO();
            charityProjectResponseDTO.setNameProject(d.getDuAnTuThien().getTenDuAn());
            charityProjectResponseDTO.setId(d.getDuAnTuThien().getMaDuAn());
            charityProjectResponseDTO.setUser(uResponseDTO);
            joinProjectResponseDTO.setUser(userResponseDTO);
            joinProjectResponseDTO.setProject(charityProjectResponseDTO);
            joinProjectResponseDTO.setContributionAmount(d.getSoTienDongGop());
            joinProjectResponseDTO.setContributionOther(d.getCacDongGopKhac());
            joinProjectResponseDTO.setNgayThamGia(d.getNgayTao());
            joinProjectResponseDTO.setRole(d.getMaVaiTroThamGiaDA().getTenVaiTro());
            joinProjectResponseDTO.setRoleId(d.getMaVaiTroThamGiaDA().getMaVaiTroThamGiaDA());
            joinProjectResponseDTOs.add(joinProjectResponseDTO);
        });
        return new ResponseEntity<>(joinProjectResponseDTOs, HttpStatus.OK);
    }
}
