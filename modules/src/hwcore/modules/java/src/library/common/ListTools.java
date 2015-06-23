/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package hwcore.modules.java.src.library.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public abstract class ListTools {

    /**
     * Metodo per clonare una lista
     *
     * @param dest lista di destinazione
     * @param src lista da cui copiare i dati
     */
    public static <T> void cloneList(List<T> dest, List<? extends T> src) {
        dest.clear();

        // Tipo Aggiungi
        dest.addAll(src);

        // Tipo Copy
        //Collections.copy(dest,src);
        // Tipo Clone ( esegue un copyOf )
        //ArrayList<T> tmp  = (ArrayList<T>) src;
        //dest = (List<T>) tmp.clone();
        // Passaggio lista al nuovo array ( esegue un copyOf )
        // dest = new ArrayList<T>(src);
    }

    /**
     * Effettua un confronto dei dati da una lista all'altra e restituisce una
     * nuova lista con tutti i dati di "comparator" esclusi quelli di "list".
     *
     * @param <T>
     * @param list la lista contenente i dati da cercare
     * @param comparator lista che contiene i dati per effettuare la ricerca
     * @return una nuova lista che a'� il risultato dell'esclusione dei dati di
     * list dal comparator
     */
    public static <T extends Comparable<T>> List<T> listBinaryExclusion(List<T> list, List<T> comparator) {
        List<T> result = new ArrayList<T>();
        List<T> copiedList = new ArrayList<T>();

        cloneList(copiedList, list);
        if (list != null) {
            Collections.sort(copiedList); // viene ordinata la copia ma non la lista originale
            for (T D : comparator) {
                if (Collections.binarySearch(copiedList, D, null) < 0) {
                    result.add(D);
                }
            }
        }
        return result;
    }

    /**
     * Per Debug: aumenta le dimensioni di una lista per testare la gestione
     * della memoria.
     *
     * @param <T>
     * @param list lista da ingrandire
     * @param multipler moltiplica la dimensione della lista per il valore
     * passato
     */
    public static <T> void ListCacheBenchmark(List<T> list, int multipler) {
        List<T> copy = new ArrayList<T>();
        cloneList(copy, list);

        for (int i = 0; i < multipler; i++) {
            list.addAll(copy);
        }
    }

    public static <T, K, V> Map<K, V> filterMapByValues(Map<K, V> map, Class<T> filter, boolean strict) {

        Map<K, V> filteredMap = new HashMap<>();

        for (Entry<K, V> entry : map.entrySet()) {
            if ((!strict && filter.isInstance(entry)) || (strict)) {
                filteredMap.put(entry.getKey(), entry.getValue());
            }
        }

        return filteredMap;
    }
}
