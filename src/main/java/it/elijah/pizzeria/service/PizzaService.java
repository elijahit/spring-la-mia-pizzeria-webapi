package it.elijah.pizzeria.service;

import java.util.List;

import it.elijah.pizzeria.model.Pizza;
import it.elijah.pizzeria.projections.PizzaApi;

public interface PizzaService {
  
  public List<PizzaApi> getMultiplePizze();

  public PizzaApi getPizza(Integer id);

  public PizzaApi editPizza(Integer id, Pizza bodyPizza);

  public String deletePizza(Integer id);
  
  public PizzaApi createPizza(Pizza pizza);
}
