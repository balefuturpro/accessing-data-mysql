package com.example.accessingdatamysql;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping(path="/BOUTIQUE")
public class ProduitController {
	@Autowired
	private ProduitRepository repository;
 
	@GetMapping(path="/produits")
	public List<Produit> getProduit()
	{
		return repository.findAll();
		
	}
	
	@PostMapping(path="/add")
	public @ResponseBody Produit saveProduit(@RequestBody Produit p)
	{
		return repository.save(p);
	}
	
	@PutMapping("/update/{id}")
	public @ResponseBody Produit updateProduit(@RequestBody Produit p,@PathVariable(value="id") int id)
	{
		Produit produit=repository.getOne(id);
		produit.setNom(p.getNom());
		produit.setPrix(p.getPrix());
		produit.setQuantite(p.getQuantite());
		return repository.save(produit);
	}
	
	@DeleteMapping(path="delete/{id}")
	public @ResponseBody void deleteProduit(@PathVariable(value="id") int id)
	{
		Produit prod=null;
		Produit p= repository.getOne(id);
		repository.delete(p);
		
	}

	@GetMapping(path="produits/{id}")
	public @ResponseBody Optional<Produit> getProduitBy(@PathVariable(value="id") int id)
	{
		return repository.findById(id);
		
	}
	

}
