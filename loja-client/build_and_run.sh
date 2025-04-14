#!/bin/bash

# Caminho base
SRC_DIR="src"
OUT_DIR="out"

# Parâmetros
MAIN_CLASS="cadeia.loja.LojaSocketClient"
LOJA_NOME="${1:-LojaRemota}"
LOJA_ID="${2:-0}"

# Limpa build anterior
echo "🧹 Limpando build anterior..."
rm -rf $OUT_DIR
mkdir -p $OUT_DIR

# Encontra todos os arquivos .java
echo "🔍 Encontrando arquivos Java..."
find $SRC_DIR -name "*.java" > sources.txt

# Compila os arquivos
echo "⚙️ Compilando..."
javac -d $OUT_DIR @sources.txt

# Verifica se compilou corretamente
if [ $? -ne 0 ]; then
  echo "❌ Erro na compilação."
  rm sources.txt
  exit 1
fi

# Executa a loja com os argumentos corretos
echo "🚀 Executando $MAIN_CLASS com loja '$LOJA_NOME' e ID '$LOJA_ID'..."
java -cp $OUT_DIR $MAIN_CLASS "$LOJA_NOME" "$LOJA_ID"

# Limpa o arquivo temporário
rm sources.txt
