package org.itis.androidgenerate.unitgeneration;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class EvoSuite {

    public ProcessRunner processRunner = new ProcessRunner();

    public void generation(String beginPath, String exportFolder, String path) throws IOException {
        doDotClass(path);
        String filePath = fileGenerate(beginPath);

        //вместо path другое(посмотреть)
        processRunner.execute(filePath, exportFolder, path);

    }


    public String fileGenerate(String beginPath) {

        String pathForGenerate = beginPath + "EvoSuitePath12345.txt";
        String filePath = beginPath + ";C:\\Program Files\\Java\\jdk1.8.0_152\\jre\\lib\\charsets.jar;C:\\Program Files\\Java\\jdk1.8.0_152\\jre\\lib\\deploy.jar;C:\\Program Files\\Java\\jdk1.8.0_152\\jre\\lib\\ext\\access-bridge-64.jar;C:\\Program Files\\Java\\jdk1.8.0_152\\jre\\lib\\ext\\cldrdata.jar;C:\\Program Files\\Java\\jdk1.8.0_152\\jre\\lib\\ext\\dnsns.jar;C:\\Program Files\\Java\\jdk1.8.0_152\\jre\\lib\\ext\\jaccess.jar;C:\\Program Files\\Java\\jdk1.8.0_152\\jre\\lib\\ext\\jfxrt.jar;C:\\Program Files\\Java\\jdk1.8.0_152\\jre\\lib\\ext\\localedata.jar;C:\\Program Files\\Java\\jdk1.8.0_152\\jre\\lib\\ext\\nashorn.jar;C:\\Program Files\\Java\\jdk1.8.0_152\\jre\\lib\\ext\\sunec.jar;C:\\Program Files\\Java\\jdk1.8.0_152\\jre\\lib\\ext\\sunjce_provider.jar;C:\\Program Files\\Java\\jdk1.8.0_152\\jre\\lib\\ext\\sunmscapi.jar;C:\\Program Files\\Java\\jdk1.8.0_152\\jre\\lib\\ext\\sunpkcs11.jar;C:\\Program Files\\Java\\jdk1.8.0_152\\jre\\lib\\ext\\zipfs.jar;C:\\Program Files\\Java\\jdk1.8.0_152\\jre\\lib\\javaws.jar;C:\\Program Files\\Java\\jdk1.8.0_152\\jre\\lib\\jce.jar;C:\\Program Files\\Java\\jdk1.8.0_152\\jre\\lib\\jfr.jar;C:\\Program Files\\Java\\jdk1.8.0_152\\jre\\lib\\jfxswt.jar;C:\\Program Files\\Java\\jdk1.8.0_152\\jre\\lib\\jsse.jar;C:\\Program Files\\Java\\jdk1.8.0_152\\jre\\lib\\management-agent.jar;C:\\Program Files\\Java\\jdk1.8.0_152\\jre\\lib\\plugin.jar;C:\\Program Files\\Java\\jdk1.8.0_152\\jre\\lib\\resources.jar;C:\\Program Files\\Java\\jdk1.8.0_152\\jre\\lib\\rt.jar;" + beginPath;
        try (FileWriter writer = new FileWriter(pathForGenerate, false)) {
            writer.write(filePath);
            writer.flush();
        } catch (IOException ex) {
        }

        return pathForGenerate;
    }

    public void doDotClass(String path) throws IOException {

        ProcessBuilder builder = new ProcessBuilder("javac", path);
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) {
                break;
            }
        }
    }
}
