{{- define "java-backend-basics.name" -}}
{{- default .Chart.Name .Values.nameOverride | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{- define "java-backend-basics.fullname" -}}
{{- printf "%s-%s" .Release.Name (include "java-backend-basics.name" .) | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{- define "java-backend-basics.labels" -}}
app.kubernetes.io/name: {{ include "java-backend-basics.name" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
helm.sh/chart: {{ .Chart.Name }}-{{ .Chart.Version | replace "+" "_" }}
{{- end -}}
