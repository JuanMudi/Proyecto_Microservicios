FROM quay.io/keycloak/keycloak:26.0.5

ENV KC_DB=mysql \
    KC_DB_PASSWORD=keyclo@k-ms \
    KC_BOOTSTRAP_ADMIN_USERNAME=admin \
    KC_BOOTSTRAP_ADMIN_PASSWORD=admin \
    KEYCLOAK_IMPORT=/opt/keycloak/data/import/landmates-kc.json

WORKDIR /opt/keycloak

COPY landmates-kc.json /opt/keycloak/data/import/

EXPOSE 9090

ENTRYPOINT ["bin/kc.sh", "--verbose", "start-dev", "--http-port", "9090", "--http-enabled", "true", "--import-realm"]
