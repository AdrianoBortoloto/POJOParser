package br.com.involves.javachallenge;

import java.io.OutputStream;

/**
 *
 * @author adriano
 */
public interface Exportable {

    public OutputStream export(Object obj);

}
