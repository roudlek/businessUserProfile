package com.ex.business.users.Controller;

import com.ex.business.users.DTO.UserProfileDTO;
import com.ex.business.users.Entities.UserProfile;
import com.ex.business.users.Services.UsersServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/templates")
public class UsersPages {
    final private UsersServiceImpl usersServiceImpl;

    public UsersPages(UsersServiceImpl usersServiceImpl) {
        this.usersServiceImpl = usersServiceImpl;
    }

    @GetMapping("/users")
    public String getUsersPage(Model model){
        List<UserProfileDTO> userProfileDTOList = usersServiceImpl.getAllUsersUsingDTO();
        model.addAttribute("userProfileDTOList",userProfileDTOList);
        return "UsersPages/users";
    }

    //not working yet
//    @GetMapping("/usersPaginated")
//    public String getUsersPagePaginated(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
//        Pageable pageable = PageRequest.of(page, size);
//        Page<UserProfile> userProfileDTOPage  = usersServiceImpl.getUserProfilesByPaginationAndDTO(pageable);
//        model.addAttribute("userProfileDTOPage ",userProfileDTOPage );
//        return "UsersPages/usersPaginated";
//    }

    @GetMapping("/users/{id}")
    public String getUserById(Model model, @PathVariable(name = "id") Long id){
        Optional<UserProfile> userProfile = usersServiceImpl.findUserById(id);
        model.addAttribute("userProfile",userProfile);
        return "UsersPages/user";
    }
}
