package org.itis.androidgenerate.unitgeneration;

import java.util.ArrayList;
import java.util.List;

public class EvoSuiteExecutor {

    Utils utils = new Utils();
    SpawnProcessKeepAliveCheckerIntelliJ spawn;

    public String[] command(String filePath, String exportFolder, String evosuite) {

        List<String> list = new ArrayList<>();
        list.add(utils.getJavaFolder() + "\\bin\\java.exe");
        list.add("-jar");
        list.add(evosuite);
        list.add("-continuous");
        list.add("execute");
        list.add("-Dspawn_process_manager_port=" + String.valueOf(spawnProcess()));
        list.add("-Dctg_memory=2000");
        list.add("-Dctg_cores=1");
        list.add("-Dctg_time_per_class=3");
        list.add("-Dctg_export_folder=" + exportFolder);
        list.add("-Dctg_selected_cuts=" + "Pin");
        list.add("-DCP_file_path=" + filePath);

        String[] s = list.toArray(new String[list.size()]);
        System.out.println(list.size());

        return s;

    }

    public int spawnProcess() {

        spawn = new SpawnProcessKeepAliveCheckerIntelliJ();
        int port = spawn.startServer();
        return port;

    }

    public void stopServer() {
        spawn.stopServer();

    }
}
