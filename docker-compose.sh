#!/bin/bash

# Verifica se a ação foi fornecida
if [[ -z "$1" ]]; then
  echo "Erro: Especifique a ação (up ou down)"
  echo "Uso: $0 <up|down> [development|production]"
  exit 1
fi

ACTION="$1"
PROFILE="${2:-development}"

# Validações
if [[ "$ACTION" != "up" && "$ACTION" != "down" ]]; then
  echo "Erro: Ação inválida. Use 'up' ou 'down'"
  exit 1
fi

if [[ "$PROFILE" != "development" && "$PROFILE" != "production" ]]; then
  echo "Erro: Perfil inválido. Use 'development' ou 'production'"
  exit 1
fi

echo "Perfil selecionado: $PROFILE"

# Executa o comando correspondente
if [[ "$ACTION" == "up" ]]; then
  echo "Subindo containers com perfil: $PROFILE"
  PROFILE=$PROFILE docker compose -f docker-compose.yml up -d
else
  echo "Parando e removendo containers com perfil: $PROFILE"
  PROFILE=$PROFILE docker compose -f docker-compose.yml down
fi

# Verifica se o comando foi executado com sucesso
if [[ $? -eq 0 ]]; then
  echo "Comando executado com sucesso!"
else
  echo "Erro ao executar o comando."
  exit 1
fi