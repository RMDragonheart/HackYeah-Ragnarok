#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "UTM_1992_2000.h"

int main(int argc,  char** argv)
{
    printf("GeoConver v0.1\n\n");

    double easting = strtod(argv[1], NULL);
    double northing = strtod(argv[2], NULL);
    double lat = 0;
    double lon = 0;

    PUWGToLatLonWGS84(easting, northing, 2, lat, lon);

    printf("X: %lf, ", easting);
    printf("Y: %lf\n", northing);

    printf("Lat: %lf, ", lat);
    printf("Lon: %lf\n\n", lon);

    return 0;
}