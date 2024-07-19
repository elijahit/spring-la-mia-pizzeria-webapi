package it.elijah.pizzeria.projections;

import java.time.LocalDate;
import java.util.List;

public interface PizzaApi {
  public Integer getId();
  public String getNome();
  public Double getPrezzo();
  public String getDescrizione();
  public String getFotoUrl();
  public List<PizzaApiIngredients> getIngredients();
  public List<PizzaApiSpecialOffers> getSpecialOffers();

  interface PizzaApiIngredients {
    public Integer getId();
    public String getIngredient();
  }

  interface PizzaApiSpecialOffers {
    public Integer getId();
    public String getTitle();
    public LocalDate getStartDate();
    public LocalDate getEndDate();
  }
}
