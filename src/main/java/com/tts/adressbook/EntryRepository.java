package com.tts.adressbook;


import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface EntryRepository extends JpaRepository<Entry,Long>{
	Set<Entry> findByFirstNameStartingWithIgnoreCase(String firstName);
	Set<Entry> findByLastNameStartingWithIgnoreCase(String lasttName);
	Set<Entry> findByPhoneNumberStartingWithIgnoreCase(String phoneNumber);
	Set<Entry> findByEmailStartingWithIgnoreCase(String email);
	@Query(value = "select * from entry e where e.first_name like %:keyword% or e.last_name like %:keyword% or e.phone_number like %:keyword% or e.email like %:keyword%", nativeQuery = true)
	Set<Entry> findByKeyword(@Param("keyword") String keyword);
}
