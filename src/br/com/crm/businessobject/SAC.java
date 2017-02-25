/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crm.businessobject;

/**
 *
 * @author icaro
 */
public class SAC {
    private int protocolo;
    private String tipoLigacao;
    private int avaliacao;
    private String estadoChamada;

    public SAC(int protocolo, String tipoLigacao, int avaliacao, String estadoChamada) {
        this.protocolo = protocolo;
        this.tipoLigacao = tipoLigacao;
        this.avaliacao = avaliacao;
        this.estadoChamada = estadoChamada;
    }

    public int getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(int protocolo) {
        this.protocolo = protocolo;
    }

    public String getTipoLigacao() {
        return tipoLigacao;
    }

    public void setTipoLigacao(String tipoLigacao) {
        this.tipoLigacao = tipoLigacao;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getEstadoChamada() {
        return estadoChamada;
    }

    public void setEstadoChamada(String estadoChamada) {
        this.estadoChamada = estadoChamada;
    }
    
}
