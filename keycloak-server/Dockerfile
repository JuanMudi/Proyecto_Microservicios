FROM quay.io/keycloak/keycloak:26.0.5

ENV KC_BOOTSTRAP_ADMIN_USERNAME=admin \
  KC_BOOTSTRAP_ADMIN_PASSWORD=admin \
  KEYCLOAK_IMPORT=/opt/keycloak/data/import/landmates-kc.json

WORKDIR /opt/keycloak

COPY landmates-kc.json /opt/keycloak/data/import/

EXPOSE 9090

ENTRYPOINT ["bin/kc.sh", "start-dev", "--http-port", "9090", "--import-realm"]