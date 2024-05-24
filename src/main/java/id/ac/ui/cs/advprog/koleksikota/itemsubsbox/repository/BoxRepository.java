package id.ac.ui.cs.advprog.koleksikota.itemsubsbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import id.ac.ui.cs.advprog.koleksikota.itemsubsbox.model.Box;

@Repository
public interface BoxRepository extends JpaRepository<Box, String> {

}
