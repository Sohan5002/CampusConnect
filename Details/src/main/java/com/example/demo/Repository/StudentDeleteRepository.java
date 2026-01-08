package com.example.demo.Repository;

public interface StudentDeleteRepository  extends JpaRepository<StudentData, Integer>{
    @Override
    //public void deleteById(Integer id);
    void deleteByRoll(int roll);
    
}
