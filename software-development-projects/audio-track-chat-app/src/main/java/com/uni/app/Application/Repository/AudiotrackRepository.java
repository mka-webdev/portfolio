package com.uni.app.Application.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.uni.app.Application.Model.Audiotrack;

public interface AudiotrackRepository extends JpaRepository<Audiotrack, Long> {}
