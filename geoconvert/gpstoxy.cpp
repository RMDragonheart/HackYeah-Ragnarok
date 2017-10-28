#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "UTM_1992_2000.h"

int main(int argc,  char** argv)
{
    printf("GeoConver v0.1\n\n");

    double lat = strtod(argv[1], NULL);
    double lon = strtod(argv[2], NULL);
    double easting = 0;
    double northing = 0;

    LatLonToPUWGWGS84(easting, northing, lat, lon, 2);

    printf("Lat: %lf, ", lat);
    printf("Lon: %lf\n", lon);

    printf("X: %lf, ", easting);
    printf("Y: %lf\n\n", northing);

    return 0;
}