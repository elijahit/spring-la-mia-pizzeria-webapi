package it.elijah.pizzeria.controller;

import java.util.HashMap;
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

import it.elijah.pizzeria.model.Pizza;
import it.elijah.pizzeria.projections.PizzaApi;
import it.elijah.pizzeria.response.Payload;
import it.elijah.pizzeria.service.PizzaService;


@RestController
@CrossOrigin
@RequestMapping("/api/pizza")
public class PizzaRestController {

  @Autowired
  PizzaService pizzaService;

  // Restituisce un payload: {Pizza, Pizza} con le informazioni delle pizze usando un projections PizzaApi oppure un errorMessage
  @GetMapping()
  public ResponseEntity<Payload<List<PizzaApi>>> getAllPizze() {
    return new ResponseEntity<>(new Payload<>(null, pizzaService.getMultiplePizze(), HttpStatus.OK), HttpStatus.OK);
  }

  // Restituisce un payload: {Pizza} con le informazioni della pizza usando un projections PizzaApi oppure un errorMessage
  @GetMapping("/{id}")
  public Payload<PizzaApi> getPizza(@PathVariable("id") Integer id) {
    try {
      return new Payload<>(null, pizzaService.getPizza(id), HttpStatus.OK);
        
    } catch (Exception e) {
      return new Payload<>("La pizza richiesta non è stata trovata", null, HttpStatus.NOT_FOUND);

    }
  }

  // Restituisce un payload: {Pizza} con le informazione della pizza modificata oppure un errorMessage
  @PutMapping("/{id}")
  public Payload<PizzaApi> editPizza(@RequestBody Pizza pizza, @PathVariable("id") Integer id) {
    try {
      pizzaService.editPizza(id, pizza);
      return new Payload<>(null, pizzaService.getPizza(id), HttpStatus.OK);
        
    } catch (Exception e) {
      return new Payload<>("La pizza richiesta non è stata trovata", null, HttpStatus.NOT_FOUND);

    }
  }
  // Restituisce un payload: {res} con la risposta oppure un errorMessage
  @DeleteMapping("/{id}")
  public Payload<HashMap<String,String>> deletePizza(@PathVariable("id") Integer id) {
    try {
      pizzaService.deletePizza(id);
      HashMap<String,String> res = new HashMap<>();
      res.put("res", "Pizza id " + id + " eliminata con successo");
      return new Payload<>(null, res, HttpStatus.OK);
        
    } catch (Exception e) {
      return new Payload<>("La pizza richiesta non è stata trovata", null, HttpStatus.NOT_FOUND);

    }
  }

  @PostMapping()
  // Restituisce un payload: {Pizza} con la risposta oppure un errorMessage
  public Payload<PizzaApi> createPizza(@RequestBody Pizza pizza) {
    try {
      return new Payload<>(null, pizzaService.createPizza(pizza), HttpStatus.OK);
        
    } catch (Exception e) {
      return new Payload<>("La creazione non è andata a buon fine", null, HttpStatus.NOT_FOUND);

    }
  }
  
}
