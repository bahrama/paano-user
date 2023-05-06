FROM alialikhahasl/paano-back

LABEL Description = "paano back image"

RUN apt-get update

ENTRYPOINT ["/home/app/entrypoint.sh"]