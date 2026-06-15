{{- define "helm-ready-task-api.name" -}}
{{- default .Chart.Name .Values.nameOverride | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{- define "helm-ready-task-api.fullname" -}}
{{- printf "%s-%s" .Release.Name (include "helm-ready-task-api.name" .) | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{- define "helm-ready-task-api.labels" -}}
app.kubernetes.io/name: {{ include "helm-ready-task-api.name" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
helm.sh/chart: {{ .Chart.Name }}-{{ .Chart.Version | replace "+" "_" }}
{{- end -}}
