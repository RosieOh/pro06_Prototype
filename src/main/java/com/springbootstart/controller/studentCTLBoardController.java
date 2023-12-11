package com.springbootstart.controller;

import com.springbootstart.service.studentCTLBoard.StudentCTLBoardSevice;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;

@Log4j2
@Controller
@RequiredArgsConstructor
public class studentCTLBoardController {

    private final StudentCTLBoardSevice studentCTLBoardSevice;



}
