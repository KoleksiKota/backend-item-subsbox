package id.ac.ui.cs.advprog.koleksikota.itemsubsbox.repository;

import org.springframework.stereotype.Repository;

import id.ac.ui.cs.advprog.koleksikota.itemsubsbox.model.Item;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {

}
