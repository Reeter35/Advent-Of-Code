package fr.reeter.advent2020.modules;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class ModulesParser {

    @Autowired
    private Logger logger;

    // the list of modules masses
    private List<Integer> modules;

    @Autowired
    public ModulesParser(@Value("classpath:modules.input") Resource modulesInput) throws IOException {
        modules = Files.readAllLines(modulesInput.getFile().toPath()).stream().map(Integer::parseInt).collect(Collectors.toList());
    }

    /**
     * 
     */
    public List<Integer> getModules() { return modules; }
    /**
     * 
     */
    public int[] getModulesAsArray() { return (int[])modules.stream().mapToInt(i->i).toArray(); }

}