package org.apache.maven.wrapper;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Workaround for (JDK 1.6.45)
 *
 * Exception in thread "main" javax.net.ssl.SSLException: java.lang.RuntimeException: Could not generate DH keypair
 *   at com.sun.net.ssl.internal.ssl.Alerts.getSSLException(Alerts.java:190)
 *   at com.sun.net.ssl.internal.ssl.SSLSocketImpl.fatal(SSLSocketImpl.java:1747)
 *   at com.sun.net.ssl.internal.ssl.SSLSocketImpl.fatal(SSLSocketImpl.java:1708)
 *   at com.sun.net.ssl.internal.ssl.SSLSocketImpl.handleException(SSLSocketImpl.java:1691)
 *   at com.sun.net.ssl.internal.ssl.SSLSocketImpl.startHandshake(SSLSocketImpl.java:1222)
 *   at com.sun.net.ssl.internal.ssl.SSLSocketImpl.startHandshake(SSLSocketImpl.java:1199)
 *   at sun.net.www.protocol.https.HttpsClient.afterConnect(HttpsClient.java:434)
 *   at sun.net.www.protocol.https.AbstractDelegateHttpsURLConnection.connect(AbstractDelegateHttpsURLConnection.java:166)
 *   at sun.net.www.protocol.http.HttpURLConnection.getInputStream(HttpURLConnection.java:1195)
 *   at sun.net.www.protocol.https.HttpsURLConnectionImpl.getInputStream(HttpsURLConnectionImpl.java:234)
 *   at org.apache.maven.wrapper.DefaultDownloader.downloadInternal(DefaultDownloader.java:91)
 *   at org.apache.maven.wrapper.DefaultDownloader.download(DefaultDownloader.java:69)
 *   at org.apache.maven.wrapper.Installer.createDist(Installer.java:69)
 *   at org.apache.maven.wrapper.WrapperExecutor.execute(WrapperExecutor.java:149)
 *   at org.apache.maven.wrapper.MavenWrapperMain.main(MavenWrapperMain.java:53)
 * Caused by: java.lang.RuntimeException: Could not generate DH keypair
 *   at com.sun.net.ssl.internal.ssl.DHCrypt.<init>(DHCrypt.java:114)
 *   at com.sun.net.ssl.internal.ssl.ClientHandshaker.serverKeyExchange(ClientHandshaker.java:559)
 *   at com.sun.net.ssl.internal.ssl.ClientHandshaker.processMessage(ClientHandshaker.java:186)
 *   at com.sun.net.ssl.internal.ssl.Handshaker.processLoop(Handshaker.java:593)
 *   at com.sun.net.ssl.internal.ssl.Handshaker.process_record(Handshaker.java:529)
 *   at com.sun.net.ssl.internal.ssl.SSLSocketImpl.readRecord(SSLSocketImpl.java:943)
 *   at com.sun.net.ssl.internal.ssl.SSLSocketImpl.performInitialHandshake(SSLSocketImpl.java:1188)
 *   at com.sun.net.ssl.internal.ssl.SSLSocketImpl.startHandshake(SSLSocketImpl.java:1215)
 *   ... 10 more
 * Caused by: java.security.InvalidAlgorithmParameterException: Prime size must be multiple of 64,
 *     and can only range from 512 to 1024 (inclusive)
 *   at com.sun.crypto.provider.DHKeyPairGenerator.initialize(DashoA13*..)
 *   at java.security.KeyPairGenerator$Delegate.initialize(KeyPairGenerator.java:627)
 *   at com.sun.net.ssl.internal.ssl.DHCrypt.<init>(DHCrypt.java:107)
 *   ... 17 more
 *
 * http://stackoverflow.com/questions/6851461/java-why-does-ssl-handshake-give-could-not-generate-dh-keypair-exception
 */
class DefaultSSLSocketFactory extends SSLSocketFactory {

    private SSLSocketFactory def = (SSLSocketFactory) SSLSocketFactory.getDefault();
    private String[] cipherSuites;

    {
        String[] supportedCipherSuites = def.getSupportedCipherSuites();
        List<String> r = new ArrayList<String>();
        for (String cipherSuite : supportedCipherSuites) {
            if (!cipherSuite.startsWith("TLS_DHE_") && !cipherSuite.startsWith("SSL_DHE_")) {
                r.add(cipherSuite);
            }
        }
        cipherSuites = r.toArray(new String[r.size()]);

    }

    public String[] getDefaultCipherSuites() {
        return cipherSuites;
    }

    public String[] getSupportedCipherSuites() {
        return cipherSuites;
    }

    public Socket createSocket(Socket socket, String s, int i, boolean b) throws IOException {
        SSLSocket soc = (SSLSocket) def.createSocket(socket, s, i, b);
        soc.setEnabledCipherSuites(cipherSuites);
        return soc;
    }

    public Socket createSocket(String s, int i) throws IOException {
        SSLSocket soc = (SSLSocket) def.createSocket(s, i);
        soc.setEnabledCipherSuites(cipherSuites);
        return soc;
    }

    public Socket createSocket(String s, int i, InetAddress inetAddress, int i1) throws IOException {
        SSLSocket soc = (SSLSocket) def.createSocket(s, i, inetAddress, i1);
        soc.setEnabledCipherSuites(cipherSuites);
        return soc;
    }

    public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        SSLSocket soc = (SSLSocket) def.createSocket(inetAddress, i);
        soc.setEnabledCipherSuites(cipherSuites);
        return soc;
    }

    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress1, int i1) throws IOException {
        SSLSocket soc = (SSLSocket) def.createSocket(inetAddress, i, inetAddress1, i1);
        soc.setEnabledCipherSuites(cipherSuites);
        return soc;
    }

}
