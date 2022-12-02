#include "loi.h"

void delaiGauss(double moyenne, double ecartype)
{
    int u1 = rand() % 2;
    int u2 = rand() % 2;

    double x = sqrt(-2 * log(u1)) * cos(2 * M_PI * u2) * ecartype + moyenne;

    usleep(x * 1000000);
}

void delaiExponentiel(double lambda)
{
    int u = rand() % 2;

    double x = -log(u)/lambda;

    usleep(x * 1000000);
}
