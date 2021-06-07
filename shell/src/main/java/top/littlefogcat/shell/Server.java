package top.littlefogcat.shell;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 开启一个服务端socket，通过socket接收应用的指令。
 * 由于通过shell开启的Java程序具有shell权限，所以可以做到比应用更多的事情。
 *
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
class Server {
    private static final String PORT_FILE = "/data/local/tmp/socket_port.dat";
    private static final int MIN_PORT = 23368; // 初始端口
    private static final int MAX_PORT = 23599; // 最大尝试端口

    private int port = MIN_PORT; // 初始端口

    private ServerSocket server; // 服务端Socket

    /**
     * 避免端口被占用
     */
    private boolean checkValidPort() {
        while (true) {
            try {
                server = new ServerSocket(port);
            } catch (IOException e) {
                if (port < MAX_PORT) {
                    port++;
                    continue;
                } else {
                    return false;
                }
            }
            // 将端口号保存在文件中
            File portFile = new File(PORT_FILE);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(portFile))) {
                if (!portFile.exists()) {
                    // create file
                    String createFileCmd = "touch " + PORT_FILE;
                    ShellUtils.execCommand(createFileCmd, false);
                }
                writer.write(String.valueOf(port));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
    }

    private void startListen() {
        while (true) {
            try {
                Socket client = server.accept();

                DataInputStream in = new DataInputStream(client.getInputStream());
                String cmd = in.readUTF();
                ShellUtils.ServiceShellCommandResult r = ShellUtils.execCommand(cmd, false);
                DataOutputStream out = new DataOutputStream(client.getOutputStream());
                out.writeUTF(r.result == 0 ? r.successMsg : r.errorMsg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        if (!server.checkValidPort()) {
            return;
        }
        server.startListen();
    }
}
