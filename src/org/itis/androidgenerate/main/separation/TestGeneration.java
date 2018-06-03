package org.itis.androidgenerate.main.separation;

import org.itis.androidgenerate.androidgeneration.library.Main;
import org.itis.androidgenerate.unitgeneration.EvoSuite;

import java.io.File;
import java.io.IOException;

public class TestGeneration {
    private Main main;
    private ClassSeparation classSeparation;
    private EvoSuite evoSuite;
    private int q = 1;

    public TestGeneration() {
        main = new Main();
        classSeparation = new ClassSeparation();
    }

    public void generation(String beginPath) throws IOException {
        classSeparation.razdelenie(new File(beginPath));
        if (!classSeparation.getTestPath().equals("")) {
            beginPath = classSeparation.getTestPath();
        }
        for (String path : classSeparation.getAndroidClass()) {

            main.mainMethod(path, beginPath, q);
            q++;
        }

        for (String path : classSeparation.getIdeaClass()) {
            evoSuite.generation(beginPath, classSeparation.getTestPath(), "evoSuite");
        }

    }

    public boolean isNotEmpty() {

        return classSeparation.getPaths().isEmpty();

    }
}
