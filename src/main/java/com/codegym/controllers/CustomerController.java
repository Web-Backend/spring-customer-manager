package com.codegym.controllers;

import com.codegym.models.Customer;
import com.codegym.services.CustomerService;
import com.codegym.services.CustomerServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;

@Controller
public class CustomerController {
    private CustomerService customerService = new CustomerServiceImpl();

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index", "customers", this.customerService.findAll());
    }

    @GetMapping("/customer/create")
    public ModelAndView create() {
        return new ModelAndView("create", "customer", new Customer());
    }

    @PostMapping("/customer/save")
    public String save(Customer customer, RedirectAttributes redirect) {
        customer.setId((int) (Math.random() * 100000));
        customerService.save(customer);
        redirect.addFlashAttribute("success", "Saved successfully");
        return "redirect:/";
    }

    @GetMapping("/customer/{id}/edit")
    public ModelAndView edit(@PathVariable int id) {
        return new ModelAndView("edit", "customer", customerService.findById(id));
    }

    @PostMapping("/customer/update")
    public String update(Customer customer, RedirectAttributes redirect) {
        customerService.update(customer.getId(), customer);
        redirect.addFlashAttribute("success", "Updated successfully");
        return "redirect:/";
    }

    @GetMapping("/customer/{id}/delete")
    public ModelAndView delete(@PathVariable int id) {
        return new ModelAndView("delete", "customer", customerService.findById(id));
    }

    @PostMapping("customer/delete")
    public String delete(Customer customer, RedirectAttributes redirect) {
        customerService.remove(customer.getId());
        redirect.addFlashAttribute("success", "Deleted successfully");
        return "redirect:/";
    }

    @GetMapping("customer/{id}/view")
    public ModelAndView view(Customer customer) {
        return new ModelAndView("view", "customer", customerService.findById(customer.getId()));
    }
}
