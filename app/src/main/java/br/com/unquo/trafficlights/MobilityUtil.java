package br.com.unquo.trafficlights;

import android.util.Log;

/**
 * Created by HenriqueM on 25/03/2018.
 */

public class MobilityUtil {

    public static int menorId = -1;

    private static double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }


    public static double calculaMenorDistancia ( double latAtual, double lonAtual )
    {
        double index = 0;
        double distanciaMenor = 999999;
//        foreach (var semaforo in semaforos){

        for (int i = 0; i < MySingleton.getInstance().semaforos.Semaforos.size(); i++){

            double distanciaCalculada = distance(latAtual,lonAtual,
                    MySingleton.getInstance().semaforos.Semaforos.get(i).Localizacao.Coordenadas.get(1),
                    MySingleton.getInstance().semaforos.Semaforos.get(i).Localizacao.Coordenadas.get(0));
//                    distanciaEntreCoordenadas(latAtual, lonAtual, semaforos.Localizacao.Coordenadas[1], semaforos.Localizacao.Coordenadas[0]);
            if (i == menorId){
                return 99999;
            }
            if (i == 0){
                distanciaMenor = distanciaCalculada;
                menorId = i;
            }
            if (distanciaCalculada < distanciaMenor){
                distanciaMenor = distanciaCalculada;
//                menorId = MySingleton.getInstance().semaforos.Semaforos.get(i).Dados.ID;
                menorId = i;
            }
            index ++;
        }

//        Log.d("Mobility", "calculaMenorDistancia: ID - " + menorId + " Distancia - " + distanciaMenor);
        return distanciaMenor;
    }

}
