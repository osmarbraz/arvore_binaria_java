
/**
 * Implementação de Árvore Binária Encadeada.
 *
 */
public class Arvore {

    /**
     * Nó de início da árvore.
     */
    private No raiz;

    /**
     * Construtor sem parâmetro.
     *
     */
    public Arvore() {
        this.raiz = null; //Inicializa a raiz da árvore com null
    }

    /**
     * Recuperador de início.
     *
     * @return O nó do início da árvore.
     */
    public No getRaiz() {
        return raiz;
    }

    /**
     * Modificador de início.
     *
     * @param raiz Um nó a ser atribuído ao início da árvore.
     */
    public void setRaiz(No raiz) {
        this.raiz = raiz;
    }

    /**
     * Retorna o valor em String da árvore.
     *
     * @return Uma string com o valor do nós da árvore.
     */
    public String paraString() {
        return "OID: " + this + " / início: " + getRaiz();
    }

    /**
     * Insere um nó na árvore de forma ordenada.
     *
     * @param dado Um novo valor de um nó a ser inserido na árvore.
     * @param _raiz Subárvore onde ocorrerá a inclusão.
     * @return Verdadeiro se conseguiu incluir o nó na árvore.
     */
    public No inserir(No _raiz, int dado) {
        //Se _raiz está vazia
        if (_raiz == null) {
            No novo = new No();
            novo.setEsquerda(null);
            novo.setDado(dado);
            novo.setDireita(null);
            _raiz = novo;
        } else {
            //Valor menor que o dado da raiz
            if (dado < _raiz.getDado()) {
                _raiz.setEsquerda(inserir(_raiz.getEsquerda(), dado));
            } else {
                //Valor maior que o dado da raiz
                if (dado > _raiz.getDado()) {
                    _raiz.setDireita(inserir(_raiz.getDireita(), dado));
                } else {
                    System.out.println("Valor já existe na arvore");
                }
            }
        }
        return _raiz;
    }

    /**
     * Retorna o sucessor modificado de um nó de uma árvore binária.
     *
     * @param _raiz Raiz da árvore.
     * @param _sucessor Sucessor modificado.
     * @return A árvore com o novo sucessor.
     */
    private No sucessor(No _raiz, No _sucessor) {
        //Verifica se a subárvore da esquerda é diferente de nula
        if (_sucessor.getEsquerda() != null) {
            //Continua a busca do sucessor
            _sucessor.setEsquerda(sucessor(_raiz, _sucessor.getEsquerda()));
        } else {
            //Encontrou o elemento altera do dado da subárvore com o balor do sucessor.
            _raiz.setDado(_sucessor.getDado());
            //Puxa a subárvore da direita para assumir o lugar.
            _sucessor = _sucessor.getDireita();
        }
        return _sucessor;
    }

    /**
     * Remove um nó de uma árvore binária.
     *
     * @param _raiz Raiz da subárvore.
     * @param dado Valor a ser encontrado e excluído da árvore.
     * @return Retorna a árvore modificada.
     */
    public No remover(No _raiz, int dado) {
        //Verifica se a _raiz não está vazia.
        if (_raiz != null) {
            //Verifica se o valor é menor que o dado da _raiz
            if (dado < _raiz.getDado()) {
                //Percorre a subárvore da esquerda de _raiz
                _raiz.setEsquerda(this.remover(_raiz.getEsquerda(), dado));

            } else {
                //Verifica se o valor é maior que o dado da raiz
                if (dado > _raiz.getDado()) {
                    //Percorre a subárvore da direita de _raiz
                    _raiz.setDireita(this.remover(_raiz.getDireita(), dado));

                } else {
                    //Verifica se  subárvore da esquerda está nula
                    if (_raiz.getEsquerda() == null) {
                        //Puxa a subárvore da direita.
                        _raiz = _raiz.getDireita();
                    } else {
                        //Verifica se  subárvore da direta está nula
                        if (_raiz.getDireita() == null) {
                            //Puxa a subárvore da esquerda.
                            _raiz = _raiz.getEsquerda();
                        } else {
                            //Busca o sucessor da _raiz apartir da subárvore da direita.
                            _raiz.setDireita(sucessor(_raiz, _raiz.getDireita()));
                        }
                    }
                }
            }
        } else {
            System.out.println("Valor não existe na arvore");
        }
        return _raiz;
    }

    /**
     * Executa o caminhar pré ordem em árvore binária.
     *
     * Método recursivo que executa o caminhar pré ordem em árvore binária.
     * Utiliza o dado e depois visita a subárvore da esquerda e depois a
     * subárvore da direita.
     *
     * @param _raiz Raiz da subárvore.
     */
    public void caminharPre(No _raiz) {
        if (_raiz != null) {
            System.out.println("Dado=" + _raiz.getDado());
            caminharPre(_raiz.getEsquerda());
            caminharPre(_raiz.getDireita());
        }
    }

    /**
     * Retorna uma String com o caminhar pré ordem em árvore binária.
     *
     * Método recursivo que executa o caminhar pré ordem em árvore binária.
     * Utiliza o dado e depois visita a subárvore da esquerda e depois a
     * subárvore da direita.
     *
     * @param _raiz Raiz da subárvore.
     * @return Uma String com os valores do caminho na árvore.
     */
    private String caminharPreString(No _raiz) {
        String str = "";
        if (_raiz != null) {
            str = str + " " + _raiz.getDado() + " - ";
            str = str + caminharPreString(_raiz.getEsquerda());
            str = str + caminharPreString(_raiz.getDireita());
        }
        return str;
    }

    /**
     * Executa o caminhar central em árvore binária.
     *
     * Método recursivo que executa o caminhar central em árvore binária. Visita
     * a subárvore da esquerda, utiliza o dado e depois visita a subárvore da
     * direita.
     *
     * @param _raiz Raiz da subárvore.
     */
    public void caminharCentral(No _raiz) {
        if (_raiz != null) {
            caminharCentral(_raiz.getEsquerda());
            System.out.println("Dado=" + _raiz.getDado());
            caminharCentral(_raiz.getDireita());
        }
    }

    /**
     * Retorna uma String com o caminhar central em árvore binária.
     *
     * Método recursivo que executa o caminhar central em árvore binária. Visita
     * a subárvore da esquerda, utiliza o dado e depois visita a subárvore da
     * direita.
     *
     * @param _raiz Raiz da subárvore.
     * @return Uma String com os valores do caminho na árvore.
     */
    private String caminharCentralString(No _raiz) {
        String str = "";
        if (_raiz != null) {
            str = str + caminharCentralString(_raiz.getEsquerda());
            str = str + " " + _raiz.getDado() + " - ";
            str = str + caminharCentralString(_raiz.getDireita());
        }
        return str;
    }

    /**
     * Executa o caminhar pós ordem em árvore binária.
     *
     * Método recursivo que executa o caminhar pós ordem em árvore binária.
     * Visita a subárvore da esquerda, visita a subárvore da direita e depois
     * utiliza o dado.
     *
     * @param _raiz Raiz da subárvore.
     */
    public void caminharPos(No _raiz) {
        if (_raiz != null) {
            caminharPos(_raiz.getEsquerda());
            caminharPos(_raiz.getDireita());
            System.out.println("Dado=" + _raiz.getDado());
        }
    }

    /**
     * Retorna uma String com o caminhar pós ordem em árvore binária.
     *
     * Método recursivo que executa o caminhar pós ordem em árvore binária.
     * Visita a subárvore da esquerda, visita a subárvore da direita e depois
     * utiliza o dado.
     *
     * @param _raiz Raiz da subárvore.
     * @return Uma String com os valores do caminho na árvore.
     */
    private String caminharPosString(No _raiz) {
        String str = "";
        if (_raiz != null) {
            str = str + caminharPosString(_raiz.getEsquerda());
            str = str + caminharPosString(_raiz.getDireita());
            str = str + " " + _raiz.getDado() + " - ";
        }
        return str;
    }

    /**
     * Executa o caminhar pré ordem invertido em árvore binária.
     *
     * Método recursivo que executa o caminhar pré ordem invertido em árvore
     * binária. Utiliza o dado, visita a subárvore da direta e depois a visita
     * subárvore da esquerda.
     *
     * @param _raiz Raiz da subárvore.
     */
    public void caminharPreInvertido(No _raiz) {
        if (_raiz != null) {
            System.out.println("Dado=" + _raiz.getDado());
            caminharPreInvertido(_raiz.getDireita());
            caminharPreInvertido(_raiz.getEsquerda());
        }
    }

    /**
     * Retorna uma String com o caminhar pré ordem invertido em árvore binária.
     *
     * Método recursivo que executa o caminhar pré ordem invertido em árvore
     * binária. Utiliza o dado, visita a subárvore da direta e depois visita a
     * subárvore da esquerda.
     *
     * @param _raiz Raiz da subárvore.
     * @return Uma String com os valores do caminho na árvore.
     */
    private String caminharPreInvertidoString(No _raiz) {
        String str = "";
        if (_raiz != null) {
            str = str + " " + _raiz.getDado() + " - ";
            str = str + caminharPreInvertidoString(_raiz.getDireita());
            str = str + caminharPreInvertidoString(_raiz.getEsquerda());
        }
        return str;
    }

    /**
     * Executa o caminhar central invertido em árvore binária.
     *
     * Método recursivo que executa o caminhar central invertido em árvore
     * binária. Visita a subárvore da direta, utiliza o dado e depois visita a
     * subárvore da esquerda.
     *
     * @param _raiz Raiz da subárvore.
     */
    public void caminharCentralInvertido(No _raiz) {
        if (_raiz != null) {
            caminharCentralInvertido(_raiz.getDireita());
            System.out.println("Dado=" + _raiz.getDado());
            caminharCentralInvertido(_raiz.getEsquerda());
        }
    }

    /**
     * Retorna uma String com o caminhar central invertido em árvore binária.
     *
     * Método recursivo que executa o caminhar central invertido em árvore
     * binária. Visita a subárvore da direta, utiliza o dado e depois visita a
     * subárvore da esquerda.
     *
     * @param _raiz Raiz da subárvore.
     * @return Uma String com os valores do caminho na árvore.
     */
    private String caminharCentralInvertidoString(No _raiz) {
        String str = "";
        if (_raiz != null) {
            str = str + caminharCentralInvertidoString(_raiz.getDireita());
            str = str + " " + _raiz.getDado() + " - ";
            str = str + caminharCentralInvertidoString(_raiz.getEsquerda());
        }
        return str;
    }

    /**
     * Executa o caminhar pós ordem invertido em árvore binária.
     *
     * Método recursivo que executa o caminhar pós invertido em árvore binária.
     * Visita a subárvore da direta, visita a subárvore da esquerda e depois
     * utiliza o dado.
     *
     * @param _raiz Raiz da subárvore.
     */
    public void caminharPosInvertido(No _raiz) {
        if (_raiz != null) {
            caminharPosInvertido(_raiz.getDireita());
            caminharPosInvertido(_raiz.getEsquerda());
            System.out.println("Dado=" + _raiz.getDado());
        }
    }

    /**
     * Retorna uma String com o caminhar pós ordem invertido em árvore binária.
     *
     * Método recursivo que executa o caminhar pós invertido em árvore binária.
     * Visita a subárvore da direta, visita a subárvore da esquerda e depois
     * utiliza o dado.
     *
     * @param _raiz Raiz da subárvore.
     * @return Uma String com os valores do caminho na árvore.
     */
    private String caminharPosInvertidoString(No _raiz) {
        String str = "";
        if (_raiz != null) {
            str = str + caminharPosInvertidoString(_raiz.getDireita());
            str = str + caminharPosInvertidoString(_raiz.getEsquerda());
            str = str + " " + _raiz.getDado() + " - ";
        }
        return str;
    }

    /**
     * Conta os nós de uma árvore binária.
     *
     * @param _raiz
     * @return A quantidade de nós da árvore.
     */
    public int contarNo(No _raiz) {
        int cont = 0;
        //Verifica se a árvore não é nula
        if (_raiz != null) {
            cont += contarNo(_raiz.getDireita());
            cont += contarNo(_raiz.getEsquerda());
            cont += 1;
        }
        return cont;
    }

    /**
     * Procura um dado na árvore binária.
     *
     * @param _raiz Raiz de uma subárvore.
     * @param dado O valor de um nó a ser procurado na lista.
     * @return Verdadeiro se encontrou o dado na árvore binária.
     */
    public boolean procurarValor(No _raiz, int dado) {
        if (_raiz != null) {
            if (dado == _raiz.getDado()) {
                return (true);
            } else {
                if (dado < _raiz.getDado()) {
                    return (false || procurarValor(_raiz.getEsquerda(), dado));
                } else {
                    if (dado > _raiz.getDado()) {
                        return (false || procurarValor(_raiz.getDireita(), dado));
                    }
                }
            }
        }
        return false;
    }

    /**
     * Retorna uma String com os valores dos nós folhas da árvore binária.
     *
     * @param _raiz Uma raiz de uma subárvore.
     * @return Uma String com os valores dos nós folhas da árvore binária.
     */
    public String encontrarFolhas(No _raiz) {
        String str = "";
        if (_raiz != null) {
            str = str + encontrarFolhas(_raiz.getEsquerda());
            if ((_raiz.getEsquerda() == null) && (_raiz.getDireita() == null)) {
                str = str + " " + _raiz.getDado() + " - ";
            }
            str = str + encontrarFolhas(_raiz.getDireita());
        }
        return str;
    }

    /**
     * Encontra a getAltura da árvore binária.
     *
     * @param _raiz Raiz de uma subárvore.
     * @return A getAltura da árvore binária.
     */
    public int getAltura(No _raiz) {
        if (_raiz == null) {
            return 0;
        } else {
            int esquerda = getAltura(_raiz.getEsquerda());
            int direita = getAltura(_raiz.getDireita());
            if (esquerda > direita) {
                return 1 + esquerda;
            } else {
                return 1 + direita;
            }
        }
    }
}//Fim Arvore