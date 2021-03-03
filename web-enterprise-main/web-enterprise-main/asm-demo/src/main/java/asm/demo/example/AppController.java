package asm.demo.example;

import java.security.Principal;
import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import asm.demo.example.WebUtils;

@Controller
public class AppController {

	@Autowired
	private PostService service;
	
	@GetMapping("/")
    @RolesAllowed("STUDENT, ADMIN, MARKETER_F, MARKETER_M, GUEST")
    public String accessHomePage() {
        return "homePage";
    }
	

	@RequestMapping("/index")
	public String viewStudentPage(Model model) {
		List<Post> listPosts = service.listAll();
		model.addAttribute("listPosts", listPosts);
		return "index";
	}
	
	
	@RequestMapping("/new")
	public String showNewPostForm(Model model) {
	    Post post = new Post();
	    model.addAttribute("posts", post);
	     
	    return "new_post";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String savePost(@ModelAttribute("posts") Post posts) {
	    service.save(posts);
	     
	    return "redirect:/index";
	}
	
	@RequestMapping("/edit/{idpost}")
	public ModelAndView showEditPostPage(@PathVariable(name = "idpost") Long idpost) {
	    ModelAndView mav = new ModelAndView("edit_post");
	    Post post = service.get(idpost);
	    mav.addObject("post", post);
	     
	    return mav;
	}
	@RequestMapping("/delete/{idpost}")
	public String deletePost(@PathVariable(name = "idpost") Long idpost) {
	    service.delete(idpost);
	    return "redirect:/index";       
	}
	
	@GetMapping("/403")
	public String erro403() {
		return "403";
	}
	

    
	
	
	
	
	

}
