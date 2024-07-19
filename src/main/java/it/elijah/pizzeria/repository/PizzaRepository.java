package it.elijah.pizzeria.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.elijah.pizzeria.model.Pizza;
import it.elijah.pizzeria.projections.PizzaApi;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
	
	@Query("SELECT p FROM Pizza p WHERE p.nome like %:nome%")
	public List<Pizza> findByNomeIgnoreCase(String nome);
	
	@Query
	public List<PizzaApi> findAllProjectedBy();

	@Query
	public Optional<PizzaApi> findPizzaById(Integer id);
}
