package by.ghoncharko.selectioninterview.exceptionhandler;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ErrorResponse {
    private final List<String> errors = new ArrayList<>();

}
